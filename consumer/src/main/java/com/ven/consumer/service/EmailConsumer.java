package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailConsumer {
    private final String emailTopic = "email-notification";

    @KafkaListener(topics = emailTopic, groupId = "${spring.kafka.email-consumer.group-id}",
            containerFactory = "emailNotificationConcurrentKafkaListenerContainerFactory",
            autoStartup = "${spring.kafka.email-consumer.enabled}")
    public void listenEmailNotification(NotificationProto.EmailNotification message,
                                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                        @Header(KafkaHeaders.OFFSET) long offset) {
        log.info(String.format("Consumed message from topic %s, partition %d, offset %d: %s",
                emailTopic, partition, offset, message));
        // eventProcessor.processEvent(message);
    }
}
