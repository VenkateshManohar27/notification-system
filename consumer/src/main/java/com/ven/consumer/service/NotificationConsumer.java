package com.ven.consumer.service;

import com.ven.consumer.model.Client;
import com.ven.design.notification.proto.NotificationProto;

import com.ven.design.notification.proto.NotificationProto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class NotificationConsumer {

    private final String notificationTopic = "notification";
    private ClientConfigurationService clientConfigurationService;

    private final NotificationProducerFactory notificationProducerFactory;
    @Autowired
    public NotificationConsumer(ClientConfigurationService clientConfigurationService,
                                NotificationProducerFactory notificationProducerFactory) {
        this.clientConfigurationService = clientConfigurationService;
        this.notificationProducerFactory = notificationProducerFactory;
    }

    @KafkaListener(topics = notificationTopic, groupId = "${spring.kafka.notification-consumer.group-id}",
            containerFactory = "notificationEventConcurrentKafkaListenerContainerFactory",
            autoStartup = "${spring.kafka.notification-consumer.enabled}")
    public void listenNotificationEvent(NotificationEvent message,
                                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                        @Header(KafkaHeaders.OFFSET) long offset) {
        log.info(String.format("Consumed message from topic %s, partition %d, offset %d: %s",
                topic, partition, offset, message));
        Client clientDto = this.clientConfigurationService.getClientConfiguration(message.getClientId());
        log.info("Fetched client configuration: " + clientDto);
        if (clientDto == null) {
            log.error("Client configuration not found for client ID: " + message.getClientId());
            return;
        }

        if (clientDto.getNotification().isEmailEnabled()) {
            this.notificationProducerFactory.getProducer(NotificationProto.NotificationType.EMAIL)
                    .sendNotification(message, clientDto.getNotification().getEmailRecipients().stream().collect(Collectors.joining(",")));
        }
        if (clientDto.getNotification().isPhoneEnabled()) {
            this.notificationProducerFactory.getProducer(NotificationProto.NotificationType.SMS)
                    .sendNotification(message, clientDto.getNotification().getPhoneRecipients().stream().collect(Collectors.joining(",")));
        }

        if (clientDto.getNotification().isSlackEnabled()) {
            this.notificationProducerFactory.getProducer(NotificationProto.NotificationType.SLACK)
                    .sendNotification(message, clientDto.getNotification().getSlackChannel());
        }
        // eventProcessor.processEvent(message);
    }
}
