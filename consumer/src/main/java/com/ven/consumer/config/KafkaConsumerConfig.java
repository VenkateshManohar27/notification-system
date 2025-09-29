package com.ven.consumer.config;

import com.ven.consumer.serializer.EmailNotificationDeserializer;
import com.ven.consumer.serializer.NotificationEventDeserializer;
import com.ven.consumer.serializer.SlackNotificationDeserializer;
import com.ven.consumer.serializer.SmsNotificationDeserializer;
import com.ven.design.notification.proto.NotificationProto.EmailNotification;
import com.ven.design.notification.proto.NotificationProto.SmsNotification;
import com.ven.design.notification.proto.NotificationProto.SlackNotification;
import com.ven.design.notification.proto.NotificationProto.NotificationEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.notification-consumer.group-id}")
    private String notificationGroupId;

    @Value(value = "${spring.kafka.notification-consumer.key-deserializer}")
    private String notificationKeyDeserializer;

    @Value(value = "${spring.kafka.notification-consumer.value-deserializer}")
    private String notificationValueDeserializer;

    @Value(value = "${spring.kafka.email-consumer.group-id}")
    private String emailGroupId;

    @Value(value = "${spring.kafka.email-consumer.key-deserializer}")
    private String emailKeyDeserializer;

    @Value(value = "${spring.kafka.email-consumer.value-deserializer}")
    private String emailValueDeserializer;

    @Value(value = "${spring.kafka.sms-consumer.group-id}")
    private String smsGroupId;

    @Value(value = "${spring.kafka.sms-consumer.key-deserializer}")
    private String smsKeyDeserializer;

    @Value(value = "${spring.kafka.sms-consumer.value-deserializer}")
    private String smsValueDeserializer;

    @Value(value = "${spring.kafka.slack-consumer.group-id}")
    private String slackGroupId;

    @Value(value = "${spring.kafka.slack-consumer.key-deserializer}")
    private String slackKeyDeserializer;

    @Value(value = "${spring.kafka.slack-consumer.value-deserializer}")
    private String slackValueDeserializer;

    @Bean
    public ConsumerFactory<String, NotificationEvent> notificationEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, notificationGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, notificationKeyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, notificationValueDeserializer);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new NotificationEventDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> notificationEventConcurrentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, NotificationEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(notificationEventConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, EmailNotification> emailNotificationConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, emailGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, emailKeyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, emailValueDeserializer);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new EmailNotificationDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, EmailNotification> emailNotificationConcurrentKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, EmailNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(emailNotificationConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, SmsNotification> smsNotificationConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, smsGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, smsKeyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, smsValueDeserializer);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new SmsNotificationDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SmsNotification>
            smsNotificationConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SmsNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(smsNotificationConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, SlackNotification> slackNotificationConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, slackGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, slackKeyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, slackValueDeserializer);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new SlackNotificationDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SlackNotification>
            slackNotificationConcurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SlackNotification> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(slackNotificationConsumerFactory());
        return factory;
    }
}
