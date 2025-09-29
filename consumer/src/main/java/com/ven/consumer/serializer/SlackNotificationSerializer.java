package com.ven.consumer.serializer;

import com.ven.design.notification.proto.NotificationProto.SlackNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class SlackNotificationSerializer implements Serializer<SlackNotification> {
    @Override
    public byte[] serialize(String topic, SlackNotification data) {
        log.info("Serializing SlackNotification for topic: {}", topic);
        return data.toByteArray();
    }
}