package com.ohgiraffers.r_pakabe.flow.gmMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class RoomMessageService {

    private final Map<Integer, Sinks.Many<String>> roomSinks = new ConcurrentHashMap<>();

    /**
     * 특정 방(roomId)에 대한 Sink를 생성하거나 기존 Sink를 반환합니다.
     * @param roomId 방 식별자
     * @return Sinks.Many<String> 해당 방의 Sink
     */
    public Sinks.Many<String> getSinkForRoom(Integer roomId) {
        return roomSinks.computeIfAbsent(roomId, k ->
                Sinks.many().multicast().onBackpressureBuffer()
        );
    }

    /**
     * 특정 방에 메시지를 발행합니다.
     * @param roomId 방 식별자
     * @param message 발행할 메시지
     */
    public void emitToRoom(Integer roomId, String message) {
        Sinks.Many<String> sink = getSinkForRoom(roomId);
        log.info(message);
        if(sink.currentSubscriberCount() < 1){
            log.info("No Subscriber found for room id ({}), message ignored.", roomId);
            return;
        }
        Sinks.EmitResult result = null;
        try {
            result = sink.tryEmitNext(message);
        }catch (Exception e){
            log.error("Error emitting message : {}", e.toString());
            return;
        }

        switch (result) {
            case OK:
                break;
            case FAIL_OVERFLOW:
                log.error("Message queue is full for room: {}", roomId);
                break;
            case FAIL_TERMINATED:
                log.error("The sink is already terminated for room: {}", roomId);
                break;
            default:
                log.error("Failed to emit message: {}", result);
        }
    }

    /**
     * 방의 Sink를 제거합니다.
     * @param roomId 방 식별자
     */
    public void removeRoomSink(Integer roomId) {
        roomSinks.remove(roomId);
    }
}
