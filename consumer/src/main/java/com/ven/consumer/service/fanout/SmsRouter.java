package com.ven.consumer.service.fanout;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("smsRouter")
@Slf4j
public class SmsRouter implements NotificationRouter {
    private final KafkaTemplate<String, NotificationProto.SmsNotification> kafkaTemplate;

    public SmsRouter(@Qualifier("smsKafkaTemplate") KafkaTemplate<String, NotificationProto.SmsNotification> kafkaTemplate) {
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
        log.info("Route to SMS notification: {}", smsNotification);
        kafkaTemplate.send("sms-notification", smsNotification);
    }
}
