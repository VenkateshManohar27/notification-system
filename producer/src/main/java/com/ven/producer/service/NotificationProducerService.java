package com.ven.producer.service;

import com.ven.design.notification.proto.NotificationProto.NotificationEvent;
import com.ven.producer.dto.NotificationEventDto;
import com.ven.producer.exception.NotificationEventException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class NotificationProducerService {
    private final KafkaTemplate<String, NotificationEvent> kafkaTemplate;
    private final String topic = "notification";

    /*private final Map<NotificationEvent.NotificationType, String> notificationTypeTopicMap = Map.of(
            NotificationEvent.NotificationType.EMAIL, "email-notification",
            NotificationEvent.NotificationType.SMS, "sms-notification",
            NotificationEvent.NotificationType.SLACK, "slack-notification"
    );*/


    @Autowired
    public NotificationProducerService(KafkaTemplate<String, NotificationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public NotificationEvent sendMessage(NotificationEventDto dto) {
        log.info("Producing message to topic {}: {}", topic, dto);
        NotificationEvent event = NotificationEvent.newBuilder()
                .setId(dto.getId())
                .setClientid(dto.getClientId())
                .setMessage(dto.getMessage())
                .setTimestamp(Instant.now().toEpochMilli())
                .build();

        CompletableFuture<SendResult<String, NotificationEvent>> future = kafkaTemplate.send(topic, event);
        future.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to send message: {}", ex.getMessage());
                throw new NotificationEventException(ex.getMessage());
            } else {
                log.info("Message sent to topic {} with offset {}", result.getRecordMetadata().topic(), result.getRecordMetadata().offset());
            }
        });
        return event;
    }
}