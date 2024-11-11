package com.ohgiraffers.r_pakabe.flow.aiComm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AiConnectionService {

    private final WebClient webClient;

    private static final String baseUri = "https://boss-goblin-tolerant.ngrok-free.app";

    @Autowired
    public AiConnectionService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getData(String targetUri) {
        return webClient.get()
                .uri(baseUri + targetUri)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> postData(Object requestBody, String targetUri) {
        return webClient.post()
                .uri(baseUri + targetUri)
                .body(Mono.just(requestBody), Object.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
