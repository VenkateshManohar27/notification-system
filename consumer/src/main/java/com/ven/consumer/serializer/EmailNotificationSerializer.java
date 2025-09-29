package com.ven.consumer.serializer;

import com.ven.design.notification.proto.NotificationProto.EmailNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class EmailNotificationSerializer implements Serializer<EmailNotification> {
    @Override
    public byte[] serialize(String topic, EmailNotification data) {
        log.info("Serializing EmailNotification for topic: {}", topic);
        return data.toByteArray();
    }
}