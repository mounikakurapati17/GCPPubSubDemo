package com.example.DemoApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Slf4j
@Service
public class PubSubPublisherService{

    private final PubSubTemplate pubSubTemplate;

    @Autowired
    public PubSubPublisherService(PubSubTemplate pubSubTemplate) {
        this.pubSubTemplate = pubSubTemplate;
    }


    public void publish(String topicName, String event) {

        ListenableFuture<String> future = pubSubTemplate.publish(topicName, event);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while publishing");
            }

            @Override
            public void onSuccess(String result) {
                  log.info("Success publishing message with ID" + result);
            }
        });
    }
}

