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

    // Getters and Setters
/*
    public boolean isEmailEnabled() {
        return isEmailEnabled;
    }

    public void setEmailEnabled(boolean emailEnabled) {
        isEmailEnabled = emailEnabled;
    }

    public List<String> getEmailRecipients() {
        return emailRecipients;
    }

    public void setEmailRecipients(List<String> emailRecipients) {
        this.emailRecipients = emailRecipients;
    }

    public boolean isPhoneEnabled() {
        return isPhoneEnabled;
    }

    public void setPhoneEnabled(boolean phoneEnabled) {
        isPhoneEnabled = phoneEnabled;
    }

    public List<String> getPhoneRecipients() {
        return phoneRecipients;
    }

    public void setPhoneRecipients(List<String> phoneRecipients) {
        this.phoneRecipients = phoneRecipients;
    }

    public boolean isSlackEnabled() {
        return isSlackEnabled;
    }

    public void setSlackEnabled(boolean slackEnabled) {
        isSlackEnabled = slackEnabled;
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public void setSlackChannel(String slackChannel) {
        this.slackChannel = slackChannel;
    }*/
}
