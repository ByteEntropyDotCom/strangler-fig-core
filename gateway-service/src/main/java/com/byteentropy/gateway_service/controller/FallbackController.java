package com.byteentropy.gateway_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/orders")
    public Mono<Map<String, String>> ordersFallback() {
        return Mono.just(Map.of(
            "status", "Temporary Unavailable",
            "message", "The modern order service is currently down. This is the Gateway fallback.",
            "source", "Gateway-Circuit-Breaker"
        ));
    }
}