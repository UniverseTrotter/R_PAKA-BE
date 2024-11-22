package com.ohgiraffers.r_pakabe.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.ServerSentEventHttpMessageWriter;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class WebFluxConfig implements WebFluxConfigurer {

    @Override
    public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
        // UTF-8 인코딩을 사용하는 CharSequenceEncoder 생성
        CharSequenceEncoder encoder = CharSequenceEncoder.textPlainOnly();

        // 커스텀 ServerSentEventHttpMessageWriter 생성
        ServerSentEventHttpMessageWriter sseWriter = new ServerSentEventHttpMessageWriter(encoder);

        // 기존의 ServerSentEventHttpMessageWriter를 제거하고 커스텀한 것으로 대체
        List<HttpMessageWriter<?>> writers = configurer.getWriters();
        writers.removeIf(writer -> writer instanceof ServerSentEventHttpMessageWriter);
        writers.add(sseWriter);
    }
}
