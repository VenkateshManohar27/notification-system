package com.ven.consumer.serializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ven.design.notification.proto.NotificationProto.SmsNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class SmsNotificationDeserializer implements Deserializer<SmsNotification> {
    @Override
    public SmsNotification deserialize(String topic, byte[] data) {
        try {
            return SmsNotification.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserialize SmsNotification", e);
            throw new RuntimeException("exception while parsing");
        }
    }
}