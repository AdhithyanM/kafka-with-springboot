package com.adhithyan.kafkawithspringboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that sets up Kafka-related beans.
 */
@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic testTopic2() {
        // Create a new topic named "test-topic-2" with 5 partitions and a replication factor of 1.
        return new NewTopic("test-topic-2", 5, (short) 1);
    }

    @Bean
    public NewTopic customerTopic() {
        // Create a new topic named "customer-topic" with 3 partitions and a replication factor of 1.
        return new NewTopic("customer-topic", 3, (short) 1);
    }
}
