package com.ven.consumer.service;

import com.ven.consumer.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClientConfigurationService {
    private final WebClient webClient;

    public ClientConfigurationService(WebClient clientServiceWebClientConfig) {
        this.webClient = clientServiceWebClientConfig;
    }

    public Client getClientConfiguration(String clientId) {
        return webClient.get()
                .uri(clientId)
                .retrieve()
                .bodyToMono(Client.class)
                .block(); // Use .block() for synchronous call, or subscribe for reactive
    }

}
