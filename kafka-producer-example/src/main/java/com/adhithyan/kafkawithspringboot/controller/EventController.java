package com.adhithyan.kafkawithspringboot.controller;

import com.adhithyan.kafkawithspringboot.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(
            @PathVariable("message") String message
    ) {
        try {
            // This is for explaining how kafka segregates the messages into multiple partitions to balance the load given to it.
            for(int i=0; i<10000; i++) {
                publisher.sendMessageToTopic(message+" "+i);
            }
            return ResponseEntity.ok("Message published successfully..");
        } catch (Exception ex) {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
