package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsConsumer {
    private final String smsTopic = "sms-notification";

    @KafkaListener(topics = smsTopic, groupId = "${spring.kafka.sms-consumer.group-id}",
            containerFactory = "smsNotificationConcurrentKafkaListenerContainerFactory",
            autoStartup = "${spring.kafka.sms-consumer.enabled}")
    public void listenSmsNotification(NotificationProto.SmsNotification message,
                                      @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                      @Header(KafkaHeaders.OFFSET) long offset) {
        log.info(String.format("Consumed message from topic %s, partition %d, offset %d: %s",
                topic, partition, offset, message));
        // eventProcessor.processEvent(message);
    }
}
