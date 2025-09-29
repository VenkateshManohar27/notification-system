package com.ven.consumer.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private boolean isEmailEnabled;
    @NotEmpty(message = "Email list cannot be empty")
    @Valid // Crucial for validating elements within the list
    private List<@Email(message = "Invalid email format") String> emailRecipients;

    private boolean isPhoneEnabled;
    private List<String> phoneRecipients;

    private boolean isSlackEnabled;
    private String slackChannel;

}
