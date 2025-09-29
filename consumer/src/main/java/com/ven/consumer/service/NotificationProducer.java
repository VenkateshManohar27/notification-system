package com.ven.consumer.service;

import com.ven.design.notification.proto.NotificationProto.NotificationEvent;

public interface NotificationProducer {
    void sendNotification(NotificationEvent notification, String message);
}
