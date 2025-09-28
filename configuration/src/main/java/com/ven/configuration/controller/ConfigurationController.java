package com.ven.configuration.controller;

import com.ven.configuration.model.Client;
import com.ven.configuration.model.ClientDto;
import com.ven.configuration.service.ConfigurationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: Add validation
//TODO: Add error handling
//TODO: Add logging
//TODO: Add unit tests
//TODO: Add integration tests
//TODO: Add Swagger documentation
//TODO: Add security
//TODO: Add caching
//TODO: Reduce usage of streams
//TODO: Mark objects for deletion

/**
 * Controller for managing client configurations.
 * Provides endpoints to create, retrieve, and update client configurations.
 * Uses ConfigurationService to handle business logic.
 */
@RestController
@Slf4j
@RequestMapping("/api/configuration/client")
public class ConfigurationController {

    ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    /**
     * Saves a new client configuration.
     *
     * @param client
     * @return
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(summary = "Create a new client configuration")
    public ClientDto saveClient(@RequestBody @Valid ClientDto client) {
        log.info("Received request to save client: {}", client);
        return configurationService.saveClient(client);
    }

    /**
     * Fetches all client configurations.
     * @return
     */
    @GetMapping("/all")
    @Operation(summary = "Fetch all client configurations")
    public List<ClientDto> getAllClientConfiguration() {
        log.info("Received request to fetch all client configurations");
        return configurationService.getAllClientConfiguration();
    }

    /**
     * Fetches a client configuration by its ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @Operation(summary = "Fetch client configuration by ID")
    public Client getClientById(@RequestParam String id) {
        log.info("Received request to fetch client configuration for id: {}", id);
        return configurationService.getClientById(id);
    }

    /**
     * Updates an existing client configuration.
     * @param id
     * @param client
     * @return
     */
    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Update an existing client configuration")
    public ClientDto updateClient(@RequestParam @NotNull String id, @RequestBody @Valid ClientDto client) {
        log.info("Received request to update client configuration for id: {}", id);
        return configurationService.updateClient(id, client);
    }

    @PatchMapping("/deactivate/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Deactivate an existing client configuration")
    public ClientDto updateClient(@RequestParam @NotNull String id) {
        log.info("Received request to update client configuration for id: {}", id);
        return configurationService.deactivateClient(id);
    }
}
