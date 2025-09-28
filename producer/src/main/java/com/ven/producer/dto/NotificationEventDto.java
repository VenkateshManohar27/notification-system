package com.ven.producer.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEventDto {
    private Integer id;
    @NotNull
    @NotBlank(message = "Message cannot be blank")
    private String message;
    @NotNull
    @NotBlank(message = "ClientId cannot be blank")
    private String clientId;
    private boolean read;
    @NotNull
    private Long timestamp;

}
