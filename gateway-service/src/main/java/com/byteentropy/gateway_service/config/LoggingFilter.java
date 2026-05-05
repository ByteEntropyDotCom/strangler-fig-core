package com.byteentropy.gateway_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class LoggingFilter implements GlobalFilter {

    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Capture the incoming request path
        String path = exchange.getRequest().getPath().toString();
        
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // Retrieve the final destination URI (after Eureka lookup and path rewriting)
            URI routeUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
            
            if (routeUri != null) {
                logger.info("STRANGLER-LOG: [{}] -> Routed to: {}", path, routeUri);
            } else {
                // This happens if the Circuit Breaker triggers a fallback before routing
                logger.warn("STRANGLER-LOG: [{}] -> Routing skipped (Fallback or Error)", path);
            }
        }));
    }
}