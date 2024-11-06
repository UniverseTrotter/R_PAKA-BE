package com.ohgiraffers.r_pakabe.flow;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AiConnectionService {

    private final WebClient webClient;

    @Autowired
    public AiConnectionService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getDataTest() {
        return webClient.get()
                .uri("https://boss-goblin-tolerant.ngrok-free.app/")
                .retrieve()
                .bodyToMono(String.class);
    }


    public Mono<String> postDataTest(Object requestBody) {
        return webClient.post()
                .uri("https://boss-goblin-tolerant.ngrok-free.app/")
                .body(Mono.just(requestBody), Object.class)
                .retrieve()
                .bodyToMono(String.class);
    }
}
