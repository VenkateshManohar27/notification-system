package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationProducerFactory {
    @Autowired
    private Map<String, NotificationProducer> processors;

    public NotificationProducer getProducer(NotificationProto.NotificationType type) {
        switch (type) {
            case EMAIL:
                return processors.get("emailProducer");
            case SMS:
                return processors.get("smsProducer");
            case SLACK:
                return processors.get("slackProducer");
            default:
                throw new IllegalArgumentException("Unknown message type: " + type);
        }
    }
}
