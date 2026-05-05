package com.byteentropy.legacy_monolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class LegacyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegacyApplication.class, args);
    }

    @GetMapping("/api/users/1")
    public Map<String, String> getLegacyUser() {
        return Map.of("user", "Old User Data", "source", "Legacy-Monolith");
    }

    @GetMapping("/api/orders/1")
    public String getOldOrder() {
        return "This should be unreachable if the Gateway is working!";
    }
}