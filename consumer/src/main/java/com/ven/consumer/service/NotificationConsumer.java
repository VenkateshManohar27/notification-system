package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    private static final String topic = "notification";

    @Autowired
    public NotificationConsumer() {
    }

    @KafkaListener(topics = "notification", groupId = "${spring.kafka.consumer.group-id}")
    public void listenNotificationEvent(NotificationEvent message,
                                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                        @Header(KafkaHeaders.OFFSET) long offset) {
        log.info(String.format("Consumed message from topic %s, partition %d, offset %d: %s",
                topic, partition, offset, message));
       // eventProcessor.processEvent(message);
    }
}
