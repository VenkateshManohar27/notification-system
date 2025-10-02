package com.ven.configuration.model;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private String id;
    private boolean active;
    private String endpoint;
    private Notification notification;
}
