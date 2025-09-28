package com.ven.configuration.repository;

import com.ven.configuration.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigurationRepository extends MongoRepository<Client, String> {
}
