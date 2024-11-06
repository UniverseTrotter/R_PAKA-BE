package com.ohgiraffers.r_pakabe.flow;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class AiTestController {

    private final AiConnectionService connectionService;


    @Operation(summary = "test")
    @GetMapping("/test/ai/get")
    public ResponseEntity<Mono<String>> test() {
        Mono<String> stringMono = connectionService.getDataTest();
        return ResponseEntity.ok(stringMono);
    }

    @Operation(summary = "test")
    @PostMapping("/test/ai/post")
    public ResponseEntity<Mono<String>> testPost() {
        Mono<String> stringMono = connectionService.postDataTest("test");
        return ResponseEntity.ok(stringMono);
    }
}
