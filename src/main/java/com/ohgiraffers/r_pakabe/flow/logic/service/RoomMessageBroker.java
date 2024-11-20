package com.ohgiraffers.r_pakabe.flow.logic.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class RoomMessageBroker {
    private final ConcurrentMap<String, Sinks.Many<String>> roomSinks = new ConcurrentHashMap<>();

    public void publish(String roomId, String message) {
        roomSinks.computeIfAbsent(roomId, key -> Sinks.many().multicast().onBackpressureBuffer())
                .tryEmitNext(message);
    }

    public Flux<String> subscribe(String roomId) {
        return roomSinks.computeIfAbsent(roomId, key -> Sinks.many().multicast().onBackpressureBuffer())
                .asFlux();
    }

    public void removeRoom(String roomId) {
        roomSinks.remove(roomId);
    }
}
