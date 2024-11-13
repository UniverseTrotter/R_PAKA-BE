package com.ohgiraffers.r_pakabe.flow.aiComm.service;


import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DialogAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.DiceDialogDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RequestAnalyzeDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RoomAiStartDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiRequestPlayDTO.RequestEndDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogAnalyzedDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.DialogStartResponseDTO;
import com.ohgiraffers.r_pakabe.flow.aiComm.dto.AiResponsePlayDTO.EndDialogDTO;
import com.ohgiraffers.r_pakabe.flow.logic.dto.RequestPlayDTO;
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


    public Mono<String> startScenario(RoomAiStartDTO startDTO) {
        return webClient.post()
                .uri(baseUri + "/startScenario/")
                .body(Mono.just(startDTO), RoomAiStartDTO.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<DialogStartResponseDTO> startDialog(DialogAiStartDTO requestBody) {
        return webClient.post()
                .uri(baseUri + "/startDialog/")
                .body(Mono.just(requestBody), DialogAiStartDTO.class)
                .retrieve()
                .bodyToMono(DialogStartResponseDTO.class);
    }

    public Mono<DialogAnalyzedDTO> analyseDialog(RequestAnalyzeDTO requestBody) {
        return webClient.post()
                .uri(baseUri + "/analyzeUserMsg/")
                .body(Mono.just(requestBody), RequestAnalyzeDTO.class)
                .retrieve()
                .bodyToMono(DialogAnalyzedDTO.class);
    }


    public Mono<DialogResponseDTO> requestDialog(RequestAnalyzeDTO requestAnalyzeDTO) {
        return webClient.post()
                .uri(baseUri + "/responseDialog/")
                .body(Mono.just(requestAnalyzeDTO), RequestAnalyzeDTO.class)
                .retrieve()
                .bodyToMono(DialogResponseDTO.class);
    }

    public Mono<DialogResponseDTO> responseDice(DiceDialogDTO requestDto) {
        return webClient.post()
                .uri(baseUri + "/responseDice/")
                .body(Mono.just(requestDto), DiceDialogDTO.class)
                .retrieve()
                .bodyToMono(DialogResponseDTO.class);
    }

    public Mono<EndDialogDTO> endDialog(RequestEndDTO endDTO) {
        return webClient.post()
                .uri(baseUri + "/endDialog/")
                .body(Mono.just(endDTO), RequestEndDTO.class)
                .retrieve()
                .bodyToMono(EndDialogDTO.class);
    }

    public Mono<Object> endScenario(RequestPlayDTO.RoomNumDTO roomNumDTO) {
        return webClient.post()
                .uri(baseUri + "/endScenario/")
                .body(Mono.just(roomNumDTO), RequestPlayDTO.RoomNumDTO.class)
                .retrieve()
                .bodyToMono(Object.class);
    }
}
