/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 *
 * @author joao
 */
@Service
//@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);


    private CountDownLatch planeLatch = new CountDownLatch(1);

        
    /*@KafkaListener(topics = "users", groupId = "group_id")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }*/

    @KafkaListener(topics = "${plane.topic.name}", containerFactory = "planeKafkaListenerContainerFactory")
    public void planeListener(Plane plane) {
        System.out.println("Recieved plane message: " + plane);
        this.planeLatch.countDown();
    }

}
