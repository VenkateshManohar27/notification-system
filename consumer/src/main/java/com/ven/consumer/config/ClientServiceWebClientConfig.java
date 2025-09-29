package com.ven.consumer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientServiceWebClientConfig {

        @Value("${spring.client-config.base-url}")
        private String baseUrl;

        @Value("${spring.client-config.endpoint}")
        private String endpoint;

        @Bean
        public WebClient myServiceWebClient(WebClient.Builder webClientBuilder) {
            StringBuilder urlBuilder = new StringBuilder(baseUrl);
            if (endpoint != null && !endpoint.isEmpty()) {
                urlBuilder.append(endpoint);
            }
            return webClientBuilder
                    .baseUrl(urlBuilder.toString())
                    .build();
        }
    }
