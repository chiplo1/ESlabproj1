/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@EnableScheduling
public class SchedulingTasksApplication {
    //https://spring.io/guides/gs/scheduling-tasks/
    //tutorial aqui de scheduling
    
    public static void main(String[] args) {
        SpringApplication.run(SchedulingTasksApplication.class);
    }
}
