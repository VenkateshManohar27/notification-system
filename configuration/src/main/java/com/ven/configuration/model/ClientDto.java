package com.ven.configuration.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    @NotNull
    @NotBlank(message = "Id cannot be blank")
    private String id;

    private boolean active;

    @Size(max = 255, message = "endpoint url length should be less than 255 characters")
    private String endpoint;

    @NotNull
    private NotificationDto notificationDto;
}
