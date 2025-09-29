package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("emailProducer")
@Slf4j
public class EmailProducer implements NotificationProducer {
    private final KafkaTemplate<String, NotificationProto.EmailNotification> kafkaTemplate;

    public EmailProducer(@Qualifier("emailKafkaTemplate") KafkaTemplate<String, NotificationProto.EmailNotification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationProto.NotificationEvent notification, String recipient) {
        NotificationProto.EmailNotification emailNotification = NotificationProto.EmailNotification.newBuilder()
                .setId(notification.getId())
                .setClientId(notification.getClientId())
                .setMessage(notification.getMessage())
                .setRecipient(recipient)
                .setRead(false)
                .setTimestamp(notification.getTimestamp())
                .build();
        log.info("Producing email notification: " + emailNotification);
        kafkaTemplate.send("email-notification", emailNotification);
    }
}
