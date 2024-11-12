package com.ohgiraffers.r_pakabe.flow.aiComm.service;


import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DialogAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DiceDialogDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RequestAnalyzeDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogAnalyzedDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogStartResponseDTO;
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

    public Mono<DialogStartResponseDTO> startDialog(DialogAiStartDTO requestBody) {
        return webClient.post()
                .uri(baseUri + "/startDailog")
                .body(Mono.just(requestBody), Object.class)
                .retrieve()
                .bodyToMono(DialogStartResponseDTO.class);
    }

    public Mono<DialogAnalyzedDTO> analyseDialog(RequestAnalyzeDTO requestBody) {
        return webClient.post()
                .uri(baseUri + "/analyzeUserMsg")
                .body(Mono.just(requestBody), Object.class)
                .retrieve()
                .bodyToMono(DialogAnalyzedDTO.class);
    }


    public Mono<DialogResponseDTO> requestDialog(RequestAnalyzeDTO requestAnalyzeDTO) {
        return webClient.post()
                .uri(baseUri + "/responseDailog")
                .body(Mono.just(requestAnalyzeDTO), Object.class)
                .retrieve()
                .bodyToMono(DialogResponseDTO.class);
    }

    public Mono<DialogResponseDTO> responseDice(DiceDialogDTO requestDto) {
        return webClient.post()
                .uri(baseUri + "/responseDailog")
                .body(Mono.just(requestDto), Object.class)
                .retrieve()
                .bodyToMono(DialogResponseDTO.class);
    }
}
