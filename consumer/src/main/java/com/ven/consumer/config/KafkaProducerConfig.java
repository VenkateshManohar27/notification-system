package com.ven.consumer.config;


import com.ven.design.notification.proto.NotificationProto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka Configuration class to set up producer and topic
 */
@Configuration
public class KafkaProducerConfig {

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value(value = "${spring.kafka.producer.email-serializer}")
    private String emailSerializer;

    @Value(value = "${spring.kafka.producer.sms-serializer}")
    private String smsSerializer;

    @Value(value = "${spring.kafka.producer.slack-serializer}")
    private String slackSerializer;

    @Value(value = "${spring.kafka.producer.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value(value = "${spring.kafka.producer.properties.acks}")
    private String acks;

    @Value(value = "${spring.kafka.producer.properties.retries}")
    private String retries;


    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public ProducerFactory<String, NotificationProto.EmailNotification> emailProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                keySerializer);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                emailSerializer);
        configProps.put("schema.registry.url", schemaRegistryUrl);
        configProps.put("acks", acks);
        configProps.put("retries", retries);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, NotificationProto.EmailNotification> emailKafkaTemplate() {
        return new KafkaTemplate<>(emailProducerFactory());
    }

    @Bean
    public ProducerFactory<String, NotificationProto.SmsNotification> smsProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                keySerializer);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                smsSerializer);
        configProps.put("schema.registry.url", schemaRegistryUrl);
        configProps.put("acks", acks);
        configProps.put("retries", retries);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, NotificationProto.SmsNotification> smsKafkaTemplate() {
        return new KafkaTemplate<>(smsProducerFactory());
    }

    @Bean
    public ProducerFactory<String, NotificationProto.SlackNotification> slackProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                keySerializer);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                slackSerializer);
        configProps.put("schema.registry.url", schemaRegistryUrl);
        configProps.put("acks", acks);
        configProps.put("retries", retries);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, NotificationProto.SlackNotification> slackKafkaTemplate() {
        return new KafkaTemplate<>(slackProducerFactory());
    }
}
