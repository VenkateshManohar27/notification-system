package com.ven.consumer.service.fanout;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("slackRouter")
@Slf4j
public class SlackRouter implements NotificationRouter {
    private final KafkaTemplate<String, NotificationProto.SlackNotification> kafkaTemplate;

    public SlackRouter(@Qualifier("slackKafkaTemplate") KafkaTemplate<String, NotificationProto.SlackNotification> kafkaTemplate) {
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
        log.info("Route to Slack notification: " + slackNotification);
        kafkaTemplate.send("slack-notification", slackNotification);
    }
}
