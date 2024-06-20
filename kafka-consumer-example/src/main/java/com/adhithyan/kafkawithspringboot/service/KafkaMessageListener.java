package com.adhithyan.kafkawithspringboot.service;

import com.adhithyan.kafkawithspringboot.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "test-topic-2", groupId = "tt-group-1")
    public void consumeMessage(String message) {
        log.info("consuming the message {}", message);
    }

    @KafkaListener(topics = "customer-topic", groupId = "ct-group-1")
    public void consumeEvent(Customer customer) {
        log.info("consuming the event customer : {}", customer.toString());
    }
}
