/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 *
 * @author joao
 */
@Service
public class Prod {

    //private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    //private static final String TOPIC = "planes";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Plane> planeKafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${plane.topic.name}")
    private String planeTopicName;
    
    
    
    private Producer<String, String> producer;
    
    private Properties props = new Properties();
    
   

    public void sendMessage(String message) {

        ListenableFuture<SendResult<String, String>> future
                = kafkaTemplate.send("baeldung", message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + message
                        + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + message + "] due to : " + ex.getMessage());
            }
        });
    }

    public void sendPlaneMessage(Plane plane) {
        planeKafkaTemplate.send(planeTopicName, plane);
    }
    
    public void send(String topicName, String data) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("baeldung", data);
        
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Sent message=[" + data
                        + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + data + "] due to : " + ex.getMessage());
            }
        });
    }
    /*
    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        System.out.println(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }*/

}
