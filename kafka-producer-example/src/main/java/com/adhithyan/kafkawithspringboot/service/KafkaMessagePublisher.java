package com.adhithyan.kafkawithspringboot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import com.adhithyan.kafkawithspringboot.dto.Customer;

import java.util.concurrent.CompletableFuture;

/**
 * KafkaMessagePublisher is a service class responsible for sending messages to a Kafka topic.
 */
@Service
public class KafkaMessagePublisher {

    /**
     * The KafkaTemplate is used to send messages to a Kafka topic.
     * It is automatically wired by Spring.
     */
    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        // Send the message to the "test-topic-2" topic and get a CompletableFuture for the result.
        CompletableFuture<SendResult<String, Object>> future = template.send("test-topic-2", message);
        // Handle the result of the send operation when it completes.
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Sent message = ["+message+"] with offset = ["+result.getRecordMetadata().offset()+"]");
            } else {
                System.out.println("Unable to send message=["+message+"] due to : "+exception.getMessage());
            }
        });
    }

    public void sendMessageToTopicWithPartitionControl(String message) {
        Integer partitionNumber = 3;
        CompletableFuture<SendResult<String, Object>> future = template.send("test-topic-3", partitionNumber, null, message);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Sent message = ["+message+"] with offset = ["+result.getRecordMetadata().offset()+"]");
            } else {
                System.out.println("Unable to send message=["+message+"] due to : "+exception.getMessage());
            }
        });
    }

    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("customer-topic", customer);
            future.whenComplete((result, ex) -> {
                if(ex == null) {
                    System.out.println("Sent message = ["+customer.toString()+"] with offset = ["+result.getRecordMetadata().offset()+"]");
                } else {
                    System.out.println("Unable to send message = ["+customer.toString()+"] due to : "+ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("ERROR: "+ex.getMessage());
        }
    }
}
