package com.ven.producer.serializer;

import com.ven.design.notification.proto.NotificationProto.NotificationEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class NotificationEventSerializer implements Serializer<NotificationEvent> {
    @Override
    public byte[] serialize(String topic, NotificationEvent data) {
        log.info("Serializing NotificationEvent for topic: {}", topic);
        return data.toByteArray();
    }
}
