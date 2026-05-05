package com.byteentropy.modern_service.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    /**
     * Simulates fetching order details.
     * In a Java 21 environment with virtual threads enabled, 
     * blocking operations here are extremely lightweight.
     */
    public Map<String, Object> processOrderDetails(String id) {
        // Business logic simulation
        return Map.of(
            "orderId", id,
            "transactionId", UUID.randomUUID().toString(),
            "status", "COMPLETED",
            "engine", "Java 21 Virtual Threads",
            "timestamp", System.currentTimeMillis()
        );
    }
}