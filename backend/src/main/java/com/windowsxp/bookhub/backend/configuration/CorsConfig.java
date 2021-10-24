package com.windowsxp.bookhub.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("POST", "GET", "OPTIONS", "PUT", "DELETE")
                        .allowedOrigins("http://localhost:3000")
                        .exposedHeaders("Access-Control-Allow-Origin")
                        .allowedHeaders("*").allowCredentials(true);
            }
        };
    }
}
