package com.adhithyan.kafkawithspringboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageListener {

    @KafkaListener(topics = "test-topic-2", groupId = "tt-group-1")
    public void consume(String message) {
        log.info("consuming the message {}", message);
    }
}
