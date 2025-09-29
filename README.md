# Notification Service Project

This project is a **Spring Boot** application designed to handle notifications via different channels such as SMS, Email, and Slack. It uses **Apache Kafka** for message brokering and **Protocol Buffers (Protobuf)** for message serialization.

## Project Structure

The project is divided into two main modules:

1. **Producer Module**: Responsible for producing notifications to Kafka topics.
2. **Consumer Module**: Responsible for consuming notifications from Kafka topics and processing them.

---

## Submodules

### Producer Module

**Path**: `producer/`

The producer module is responsible for creating and sending notifications to Kafka topics. It includes the following:

- **KafkaProducerConfig**: Configures Kafka producer settings and creates topics for notifications.
- **Topics**:
    - `notification`
    - `email-notification`
    - `sms-notification`
    - `slack-notification`

#### Key Features:
- Configures Kafka producer with custom serializers.
- Creates Kafka topics dynamically using `KafkaAdmin`.

---

### Consumer Module

**Path**: `consumer/`

The consumer module is responsible for consuming notifications from Kafka topics and processing them. It includes the following:

- **KafkaProducerConfig**: Configures Kafka producer settings for specific notification types (Email, SMS, Slack).
- **NotificationConsumer**: Processes incoming Kafka messages.
- **SmsProducer**: Sends SMS notifications using Kafka.

#### Key Features:
- Separate Kafka templates for each notification type.
- Implements `NotificationProducer` interface for extensibility.

---

## Technologies Used

- **Java**: Programming language.
- **Spring Boot**: Framework for building microservices.
- **Apache Kafka**: Message broker for asynchronous communication.
- **Protocol Buffers (Protobuf)**: Serialization format for structured data.
- **Gradle**: Build tool.

---

## How to Run

### Prerequisites
- Java 17 or higher
- Apache Kafka
- Schema Registry (if using Avro/Protobuf serialization)
- Gradle

### Steps
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd notification-service

Build the project:  
./gradlew build
Start Kafka and Schema Registry.  
Run the producer and consumer modules:  
./gradlew :producer:bootRun
./gradlew :consumer:bootRun
<hr></hr>
Configuration
Kafka Properties
The following properties are configurable in application.yml or application.properties:  
spring.kafka.bootstrap-servers: Kafka broker address.
spring.kafka.producer.key-serializer: Key serializer class.
spring.kafka.producer.value-serializer: Value serializer class.
spring.kafka.producer.properties.schema.registry.url: Schema registry URL.
spring.kafka.producer.properties.acks: Acknowledgment settings.
spring.kafka.producer.properties.retries: Retry settings.
<hr></hr>
Protobuf Definitions
The project uses Protobuf for defining notification messages. The key Protobuf message types are:  
NotificationEvent
EmailNotification
SmsNotification
SlackNotification
<hr></hr>
License
This project is licensed under the MIT License.<hr></hr>
Contributors
Venkatesh Manohar (Owner)