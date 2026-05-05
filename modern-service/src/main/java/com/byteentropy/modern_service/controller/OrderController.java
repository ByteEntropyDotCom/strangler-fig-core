package com.byteentropy.modern_service.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/{id}")
    public Map<String, Object> getOrder(@PathVariable String id) {
        return Map.of(
            "orderId", id,
            "source", "Modern-Java21-Service",
            "status", "Processed via Virtual Threads (Clean Path)"
        );
    }
}