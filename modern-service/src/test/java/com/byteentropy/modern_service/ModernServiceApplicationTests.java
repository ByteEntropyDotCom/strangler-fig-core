package com.byteentropy.modern_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "eureka.client.enabled=false"
})
class ModernServiceTests {

    @Autowired
    private WebTestClient webClient;

    @Test
    void testOrderEndpoint() {
        webClient.get().uri("/orders/999")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.orderId").isEqualTo("999")
                .jsonPath("$.source").isEqualTo("Modern-Java21-Service");
    }
}