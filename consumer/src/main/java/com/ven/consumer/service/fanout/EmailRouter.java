package com.ven.consumer.service.fanout;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("emailRouter")
@Slf4j
public class EmailRouter implements NotificationRouter {
    private final KafkaTemplate<String, NotificationProto.EmailNotification> kafkaTemplate;

    public EmailRouter(@Qualifier("emailKafkaTemplate") KafkaTemplate<String, NotificationProto.EmailNotification> kafkaTemplate) {
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
        log.info("Route to email notification topic : " + emailNotification);
        kafkaTemplate.send("email-notification", emailNotification);
    }
}
