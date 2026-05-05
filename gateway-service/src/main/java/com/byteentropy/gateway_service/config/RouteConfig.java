package com.byteentropy.gateway_service.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("modern-orders-route", r -> r.path("/api/orders/**")
                .filters(f -> f
                    // 1. Rewrite /api/orders/123 to /orders/123
                    .rewritePath("/api/(?<segment>.*)", "/${segment}")
                    // 2. Add Circuit Breaker
                    .circuitBreaker(config -> config
                        .setName("ordersService")
                        .setFallbackUri("forward:/fallback/orders")))
                .uri("lb://modern-service"))
            
            .route("legacy-fallback-route", r -> r.path("/**")
                .uri("lb://legacy-monolith"))
            .build();
    }
}