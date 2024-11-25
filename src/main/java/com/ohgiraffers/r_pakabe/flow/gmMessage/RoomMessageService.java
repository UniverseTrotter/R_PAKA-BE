package com.ohgiraffers.r_pakabe.flow.gmMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomMessageService {

    private final Map<Integer, Collection<Sinks.Many<MessageWrapper>>> roomSinks = new ConcurrentHashMap<>();

    /**
     * 특정 방(roomId)에 대한 Sink를 생성하거나 기존 Sink를 반환합니다.
     * @param roomId 방 식별자
     * @return Sinks.Many<String> 해당 방의 Sink
     */
    public Sinks.Many<MessageWrapper> createSinkForRoom(Integer roomId) {
        Sinks.Many<MessageWrapper> sink = Sinks.many().multicast().onBackpressureBuffer();

        roomSinks.computeIfAbsent(roomId, k -> new ConcurrentLinkedQueue<>())
                .add(sink);

        log.info("New connection created for room {}. Total connections: {}",
                roomId, roomSinks.get(roomId).size());

        return sink;
    }

    /**
     * 특정 방에 메시지를 발행합니다.
     * @param roomId 방 식별자
     * @param message 발행할 메시지
     */
    public void emitToRoom(Integer roomId, String message) {
        Collection<Sinks.Many<MessageWrapper>> sinks = roomSinks.get(roomId);
        if (sinks == null || sinks.isEmpty()) {
            log.info("No connections in room {}", roomId);
            return;
        }

        MessageWrapper wrapper = new MessageWrapper(message);
        List<Sinks.Many<MessageWrapper>> disconnectedSinks = new ArrayList<>();

        for (Sinks.Many<MessageWrapper> sink : sinks) {
            Sinks.EmitResult result = sink.tryEmitNext(wrapper);

            if (result.isFailure()) {
                log.info("Failed to emit to a connection in room {}, marking as disconnected", roomId);
                disconnectedSinks.add(sink);
            }
        }

        if (!disconnectedSinks.isEmpty()) {
            removeDisconnectedSinks(roomId, disconnectedSinks);
        }
    }

    private void removeDisconnectedSinks(Integer roomId, List<Sinks.Many<MessageWrapper>> disconnectedSinks) {
        Collection<Sinks.Many<MessageWrapper>> sinks = roomSinks.get(roomId);
        if (sinks == null) return;

        for (Sinks.Many<MessageWrapper> sink : disconnectedSinks) {
            sinks.remove(sink);
            sink.tryEmitComplete();
        }

        log.info("Removed {} disconnected sinks from room {}. Remaining connections: {}",
                disconnectedSinks.size(), roomId, sinks.size());

        if (sinks.isEmpty()) {
            removeRoom(roomId);
        }
    }

    /**
     * 방의 Sink를 제거합니다.
     * @param roomId 방 식별자
     */
    public void removeRoom(Integer roomId) {
        Collection<Sinks.Many<MessageWrapper>> sinks = roomSinks.remove(roomId);
        if (sinks != null) {
            sinks.forEach(Sinks.Many::tryEmitComplete);
        }
        log.info("Room deleted : {}", roomId);
    }


    public void removeSinkFromRoom(Integer roomId, Sinks.Many<MessageWrapper> sink) {
        Collection<Sinks.Many<MessageWrapper>> sinks = roomSinks.get(roomId);
        if (sinks != null) {
            sinks.remove(sink);
            sink.tryEmitComplete();

            log.info("Removed sink from room {}. Remaining connections: {}", roomId, sinks.size());

            if (sinks.isEmpty()) {
                removeRoom(roomId);
            }
        }
    }
}
