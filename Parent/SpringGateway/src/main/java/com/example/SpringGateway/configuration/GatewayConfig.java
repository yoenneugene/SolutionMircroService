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
                // Route vers le backend
                .route("backend_route", r -> r.path("/api/patients/**")
                        .uri("http://localhost:8081")) // Service backend pour les patients
                .route("notes_route", r -> r.path("/api/notes/**") // Ajout de la route pour les notes
                        .uri("http://localhost:8081"))
                .route("frontend_route", r -> r.path("/**")
                        .uri("http://localhost:8082"))
                .route("frontend_route", r -> r.path("/patients")
                        .uri("http://localhost:8082")) // Redirige vers le frontend
                .build();
    }


    }
