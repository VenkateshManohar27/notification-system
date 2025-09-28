package com.ven.consumer.serializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ven.design.notification.proto.NotificationProto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class NotificationEventDeserializer implements Deserializer<NotificationEvent> {
    @Override
    public NotificationEvent deserialize(String topic, byte[] data) {
        try {
            return NotificationEvent.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserialize NotificationEvent", e);
            throw new RuntimeException("excepiton while parsing");
        }
    }
}