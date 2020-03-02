/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 8000) //5 em 5 segundos
    public void reportCurrentTime() throws IOException {
        PlaneController pl = new PlaneController();
        pl.MyGETRequest("https://opensky-network.org/api/states/all");
    	log.info("The time is now {}", dateFormat.format(new Date()));
        
        ModelAndView mv = new ModelAndView();
        pl.allPlanes(mv);
    }
}
