package com.ven.consumer.service.fanout;

import com.ven.design.notification.proto.NotificationProto.NotificationEvent;

public interface NotificationRouter {
    void sendNotification(NotificationEvent notification, String message);
}
