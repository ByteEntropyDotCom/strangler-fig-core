package com.byteentropy.gateway_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "eureka.client.enabled=false",
    "spring.cloud.discovery.enabled=false"
})
class GatewayServiceTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testCircuitBreakerFallback() {
        // We test the fallback without starting the modern-service
        // The Gateway should route to /api/orders/123 and trigger the fallback
        webClient.get().uri("/api/orders/123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("Temporary Unavailable")
                .jsonPath("$.source").isEqualTo("Gateway-Circuit-Breaker");
    }

    @Test
    void testActuatorHealth() {
        webClient.get().uri("/actuator/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.status").isEqualTo("UP");
    }
}