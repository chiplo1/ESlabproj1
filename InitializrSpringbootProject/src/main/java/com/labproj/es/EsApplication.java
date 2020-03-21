package com.labproj.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(EsApplication.class, args);
        Timer timer = new Timer();
        timer.log();
    }
}
