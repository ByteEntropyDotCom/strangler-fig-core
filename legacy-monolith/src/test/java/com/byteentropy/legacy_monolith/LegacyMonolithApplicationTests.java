package com.byteentropy.legacy_monolith;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "eureka.client.enabled=false"
})
class LegacyMonolithTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testUserEndpoint() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/users/1", String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("Legacy-Monolith");
    }
}