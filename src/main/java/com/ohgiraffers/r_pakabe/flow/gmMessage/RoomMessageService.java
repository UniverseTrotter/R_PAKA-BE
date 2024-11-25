package com.ohgiraffers.r_pakabe.flow.gmMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class RoomMessageService {

    private final Map<Integer, Sinks.Many<ServerSentEvent<messageWrapper>>> roomSinks = new ConcurrentHashMap<>();

    /**
     * 특정 방(roomId)에 대한 Sink를 생성하거나 기존 Sink를 반환합니다.
     * @param roomId 방 식별자
     * @return Sinks.Many<String> 해당 방의 Sink
     */
    public Sinks.Many<ServerSentEvent<messageWrapper>> getSinkForRoom(Integer roomId) {
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
        Sinks.Many<ServerSentEvent<messageWrapper>> sink = getSinkForRoom(roomId);
        ServerSentEvent<messageWrapper> event = ServerSentEvent.<messageWrapper>builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .event("message")
                .data(new messageWrapper(message))
                .build();

        log.info("Emitting message to room ({}): {}", roomId, message);

        Sinks.EmitResult result = sink.tryEmitNext(event);
        if (result.isFailure()) {
            log.error("메세지 전송 실패 roomId ({}): {}", roomId, result);
        }
    }

    public void emitToRoomWithCustomEvent(Integer roomId, String eventName, String message) {
        Sinks.Many<ServerSentEvent<messageWrapper>> sink = getSinkForRoom(roomId);
        ServerSentEvent<messageWrapper> event = ServerSentEvent.<messageWrapper>builder()
                .id(String.valueOf(System.currentTimeMillis()))
                .event(eventName)
                .data(new messageWrapper(message))
                .build();

        log.info("Emitting custom event {} to room {}: {}", eventName, roomId, message);

        Sinks.EmitResult result = sink.tryEmitNext(event);
        if (result.isFailure()) {
            log.error("Failed to emit message to room {}: {}", roomId, result);
        }
    }

    /**
     * 방의 Sink를 제거합니다.
     * @param roomId 방 식별자
     */
    public void removeRoomSink(Integer roomId) {
        Sinks.Many<ServerSentEvent<messageWrapper>> sink = roomSinks.remove(roomId);
        if (sink != null) {
            sink.tryEmitComplete();
            log.info("Removed and completed sink for room {}", roomId);
        }
    }

    public record messageWrapper(
            String message
    ){}
}
