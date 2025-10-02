package com.ven.configuration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private boolean isEmailEnabled;
    private List<String> emailRecipients;

    private boolean isPhoneEnabled;
    private List<String> phoneRecipients;

    private boolean isSlackEnabled;
    private String slackChannel;
}
