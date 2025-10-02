package com.ven.configuration.service;

import com.ven.configuration.exception.ClientDoesNotExistException;
import com.ven.configuration.model.Client;
import com.ven.configuration.model.ClientDto;
import com.ven.configuration.model.Notification;
import com.ven.configuration.model.NotificationDto;
import com.ven.configuration.repository.ConfigurationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConfigurationService {

    ConfigurationRepository configurationRepository;

    ModelMapper modelMapper;

    public ConfigurationService(ConfigurationRepository configurationRepository, ModelMapper modelMapper){
        this.configurationRepository = configurationRepository;
        this.modelMapper = modelMapper;
    }

    public Client getConfigValue(String key) {
        return configurationRepository.findById(key).orElse(null);
    }

    public List<ClientDto> getAllClientConfiguration() {
        log.info("Fetching all client configurations");
        List<Client> clients = configurationRepository.findAll();
        return clients.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Cacheable(value = "clients", key = "#clientDto.id")
    public ClientDto saveClient(ClientDto clientDto) {
        log.info("Saving client configuration: {}", clientDto);
        Client client = convertToEntity(clientDto);

        return convertToDto(configurationRepository.save(client));
    }

    @Cacheable(value = "clients", key = "#id")
    public Client getClientById(String id) {
        log.info("Fetching client configuration for id: {}", id);
        return configurationRepository.findById(id).orElseThrow(() -> new ClientDoesNotExistException("Client not found with id: " + id));
    }

    @CachePut(value = "clients", key = "#id")
    public ClientDto updateClient(String id, ClientDto client) {
        log.info("Updating client configuration for id: {}", id);
        Client existingClient = configurationRepository.findById(id).orElseThrow(() -> new ClientDoesNotExistException("Client not found with id: " + id));
        if (existingClient != null) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

            // Map non-null fields from userDto to existingUser
            modelMapper.map(client, existingClient);
            return convertToDto(configurationRepository.save(existingClient));
        }
        return null;
    }

    @CachePut(value = "clients", key = "#id")
    public ClientDto deactivateClient(String id) {
        log.info("Deactivating client configuration for id: {}", id);
        Client existingClient = configurationRepository.findById(id).orElseThrow(() -> new ClientDoesNotExistException("Client not found with id: " + id));
        if (existingClient != null) {
            existingClient.setActive(false);
            return convertToDto(configurationRepository.save(existingClient));
        }
        return null;
    }

    private Client convertToEntity(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        Notification notification = modelMapper.map(clientDto.getNotificationDto(), Notification.class);
        client.setNotification(notification);
        return client;
    }

    private ClientDto convertToDto(Client client) {
        ClientDto clientDto = modelMapper.map(client, ClientDto.class);
        NotificationDto notification = modelMapper.map(client.getNotification(), NotificationDto.class);
        clientDto.setNotificationDto(notification);
        return clientDto;
    }

}
