package com.ven.consumer.serializer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.ven.design.notification.proto.NotificationProto.SlackNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;

@Slf4j
public class SlackNotificationDeserializer implements Deserializer<SlackNotification> {
    @Override
    public SlackNotification deserialize(String topic, byte[] data) {
        try {
            return SlackNotification.parseFrom(data);
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to deserialize SlackNotification", e);
            throw new RuntimeException("exception while parsing");
        }
    }
}