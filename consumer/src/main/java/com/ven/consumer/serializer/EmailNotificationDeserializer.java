package com.ven.consumer.serializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ven.design.notification.proto.NotificationProto.EmailNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class EmailNotificationDeserializer implements Deserializer<EmailNotification> {
    @Override
    public EmailNotification deserialize(String topic, byte[] data) {
        try {
            return EmailNotification.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserialize EmailNotification", e);
            throw new RuntimeException("excepiton while parsing");
        }
    }
}