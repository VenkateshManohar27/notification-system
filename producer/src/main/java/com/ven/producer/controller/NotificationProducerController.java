package com.ven.producer.controller;

import com.ven.design.notification.proto.NotificationProto;
import com.ven.producer.dto.NotificationEventDto;
import com.ven.producer.service.NotificationProducerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//TODO: Add integration test
//TODO: Add security

@RestController
@Slf4j
@RequestMapping("/api/notify")
public class NotificationProducerController {
    NotificationProducerService notificationProducerService;

    public NotificationProducerController(NotificationProducerService notificationProducerService) {
        this.notificationProducerService = notificationProducerService;
    }

    /**
     * Send Notification Event
     * @param notificationEventDto
     * @return
     */
    @PostMapping
    @Operation(summary = "Send Notification Event")
    public ResponseEntity<?> sendNotification(@RequestBody @Valid NotificationEventDto notificationEventDto) {
        log.info("Received notification request: " + notificationEventDto);
        NotificationProto.NotificationEvent event = notificationProducerService.sendMessage(notificationEventDto);
        return ResponseEntity.ok(event);
    }
}
