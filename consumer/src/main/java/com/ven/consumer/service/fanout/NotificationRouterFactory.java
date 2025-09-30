package com.ven.consumer.service.fanout;

import com.ven.design.notification.proto.NotificationProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationRouterFactory {
    @Autowired
    private Map<String, NotificationRouter> routers;

    public NotificationRouter getRouter(NotificationProto.NotificationType type) {
        switch (type) {
            case EMAIL:
                return routers.get("emailRouter");
            case SMS:
                return routers.get("smsRouter");
            case SLACK:
                return routers.get("slackRouter");
            default:
                throw new IllegalArgumentException("Unknown message type: " + type);
        }
    }
}
