package com.ven.consumer.serializer;

import com.ven.design.notification.proto.NotificationProto.SmsNotification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class SmsNotificationSerializer implements Serializer<SmsNotification> {
    @Override
    public byte[] serialize(String topic, SmsNotification data) {
        log.info("Serializing SmsNotification for topic: {}", topic);
        return data.toByteArray();
    }
}