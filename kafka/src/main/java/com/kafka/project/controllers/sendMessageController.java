package com.kafka.project.controllers;

import com.kafka.project.services.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sendMessageController {
    @Autowired
    private final KafkaProducerService kafkaProducerService;

    public sendMessageController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/kafka/send")
    public ResponseEntity<String> sendMessage(@RequestHeader("message") String message) {
        System.out.println(message);
        kafkaProducerService.sendMessage("example-topic", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
