/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.Consumer;

/**
 *
 * @author joao
 */
@Service
//@Component
public class Cons {

    private final Logger logger = LoggerFactory.getLogger(Prod.class);

    private Consumer<String, String> consumer;

    private CountDownLatch planeLatch = new CountDownLatch(1);

    List<String> topics = new ArrayList<>();

    
    @KafkaListener(topics = "${message.topic.name}", groupId = "plane")
    public void consumeLogger(String mess) throws IOException {
        
        
        
        System.out.println(String.format("#### -> Consumed message -> %s", mess));
        
        
        /*topics.add("baeldung");
        
        Properties props = new Properties();
        props.put("group.id", "plane");
        // log4j.logger.org.apache.kafka = WARN;
        consumer = new KafkaConsumer<String, String>(props);
        
        
        consumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {

                    System.out.println(String.format("#### -> Consumed LOGGEEEEEEEEEEEEEEEEEEEEEER -> "));
                    System.out.print(record.value());
                }
            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        } finally {
            consumer.close();
        }*/

    }

    /*@KafkaListener(topics = "${message.topic.name}", groupId = "plane")
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
