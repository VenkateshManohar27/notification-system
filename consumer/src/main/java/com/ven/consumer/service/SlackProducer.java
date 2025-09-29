package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("slackProducer")
@Slf4j
public class SlackProducer implements NotificationProducer {
    private final KafkaTemplate<String, NotificationProto.SlackNotification> kafkaTemplate;

    public SlackProducer(@Qualifier("slackKafkaTemplate") KafkaTemplate<String, NotificationProto.SlackNotification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(NotificationProto.NotificationEvent notification, String recipient) {
        NotificationProto.SlackNotification slackNotification = NotificationProto.SlackNotification.newBuilder()
                .setId(notification.getId())
                .setClientId(notification.getClientId())
                .setMessage(notification.getMessage())
                .setChannel(recipient)
                .setRead(false)
                .setTimestamp(notification.getTimestamp())
                .build();
        log.info("Producing Slack notification: " + slackNotification);
        kafkaTemplate.send("slack-notification", slackNotification);
    }
}
