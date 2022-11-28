package com.example.DemoApp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class WebAppController {

    private PubSubPublisherService pubSubPublisherService;


    @PostMapping("/publishMessage")
    public void publishMessage(String topicName, String message) {

        pubSubPublisherService.publish(topicName, message);
    }

    @PostMapping("/receiveMessageForPushSubscription")
    public void receiveMessage(String outputMessage) {

        log.info(outputMessage);
    }

}
