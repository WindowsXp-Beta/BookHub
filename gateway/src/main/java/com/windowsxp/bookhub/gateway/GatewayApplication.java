package com.windowsxp.bookhub.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    @Bean
    public RouteLocator bookHubRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service", p -> p
                        .path("/book-service")
                        .filters(f -> f
                                .dedupeResponseHeader("Access-Control-Allow-Credentials", "RETAIN_FIRST")
                                .dedupeResponseHeader("Access-Control-Allow-Origin", "RETAIN_FIRST"))
                        .uri("lb://BOOKSEARCH"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
