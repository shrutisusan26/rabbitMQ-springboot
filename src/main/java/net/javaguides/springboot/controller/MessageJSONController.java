package net.javaguides.springboot.controller;

import net.javaguides.springboot.dto.User;
import net.javaguides.springboot.publisher.RabbitMQJSONProducer;
import net.javaguides.springboot.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJSONController {

    private RabbitMQJSONProducer jsonProducer;

    public MessageJSONController(RabbitMQJSONProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJSONMessage(@RequestBody User user){
        jsonProducer.sendJSONMessage(user);
        return ResponseEntity.ok("JSON message sent");
    }
}
