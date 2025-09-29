package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("smsProducer")
@Slf4j
public class SmsProducer implements NotificationProducer {
    private final KafkaTemplate<String, NotificationProto.SmsNotification> kafkaTemplate;

    public SmsProducer(@Qualifier("smsKafkaTemplate") KafkaTemplate<String, NotificationProto.SmsNotification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationProto.NotificationEvent notification, String recipient) {
        NotificationProto.SmsNotification smsNotification = NotificationProto.SmsNotification.newBuilder()
                .setId(notification.getId())
                .setClientId(notification.getClientId())
                .setMessage(notification.getMessage())
                .setPhoneNumbers(recipient)
                .setRead(false)
                .setTimestamp(notification.getTimestamp())
                .build();
        log.info("Producing SMS notification: {}", smsNotification);
        kafkaTemplate.send("sms-notification", smsNotification);
    }
}
