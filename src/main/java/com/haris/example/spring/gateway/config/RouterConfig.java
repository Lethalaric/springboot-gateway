package com.haris.example.spring.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfig {

    @Bean
    public RouteLocator myRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/api/v1/employees")
                        .uri("https://dummy.restapiexample.com/"))
                .route(p -> p
                        .path("/api/v1/employees2")
                        .filters(f -> f
                                .circuitBreaker(config -> config
                                        .setName("fallback-001")
                                        .setFallbackUri("forward:/fallback"))) // forward it to /fallback when error occur
                        .uri("https://dummy.restapiexample.com/"))
                .build();
    }
}
