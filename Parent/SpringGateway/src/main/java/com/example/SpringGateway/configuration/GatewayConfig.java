package com.example.SpringGateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("backend_route", r -> r.path("/api/patients/**")
                        .uri("http://localhost:8081")) // Assure-toi que ce port et ce chemin sont corrects
                .build();
    } }