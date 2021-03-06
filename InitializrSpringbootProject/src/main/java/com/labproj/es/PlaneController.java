package com.labproj.es;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@EnableScheduling
public class PlaneController {

    @Autowired
    private PlaneRepository allplanes;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private Prod producer;

    @Autowired
    private Cons consumer;

    //@Scheduled(fixedRate = 1000)
    public ModelAndView getBlog(ModelAndView mv) {
        //mv.addObject("planes", plane.getPlanes());
        mv.setViewName("index");
        System.out.println("PRINTING EHHEHE");

        return mv;
    }

    //@Scheduled(fixedRate = 1000)
    @RequestMapping("/allplanes")
    public ModelAndView allPlanes(ModelAndView mv) throws IOException {
        
        //curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2
        String url = "https://chiplo123:tareco123@opensky-network.org/api/states/all";
        //String command = "curl -s 'https://opensky-network.org/api/states/all?icao24=3c6444&icao24=3e1bf9'";
        
        logger.info("Page allplanes reloaded.");
        producer.send(loggerTopicName, new Timestamp(new Date().getTime()) + " PlaneController: Page allplanes reloaded.");
            
        
        System.out.println("CARREGUEI NO F5");

        mv.addObject("planes", allplanes.findAll());
        
        logger.info("Planes' data retrieved from database.");
        producer.send(loggerTopicName, new Timestamp(new Date().getTime()) + " PlaneController: Planes' data retrieved from database.");
            
        mv.setViewName("allPlanes");

        //MyGETRequest(url);
        return mv;

    }

    public String MyGETRequest(String url) throws IOException {
        URL urlForGetRequest = new URL(url);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();

            String rawResponse = response.toString();
            // print result
            //System.out.println("JSON String Result " + rawResponse);
            //GetAndPost.POSTRequest(response.toString());

            // Process Raw String
            System.out.println(rawResponse);

            processPlanes(rawResponse);
            logger.info("Request data from opensky-network API granted.");
            producer.send(loggerTopicName, new Timestamp(new Date().getTime()) + " PlaneController: Request data from opensky-network API granted.");
       
            return rawResponse;
        } else {
            System.out.println("GET NOT WORKED");
            logger.info("Request data from opensky-network API not granted.");
            producer.send(loggerTopicName, new Timestamp(new Date().getTime()) + " PlaneController: Request data from opensky-network API not granted.");
            
        }
        logger.info("Request data from opensky-network API not granted.");
        producer.send(loggerTopicName, new Timestamp(new Date().getTime()) + " PlaneController: Request data from opensky-network API not granted.");
        
        return "GET NOT WORKED";
    }

    public String processPlanes(String rawResponse) {

        //allplanes.deleteAllPlanes(); 
        String[] planes = rawResponse.split("\\[\"");
        for (int i = 1; i < planes.length; i++) {
            //System.out.println(planes[i]);
            String[] plane = planes[i].replace("\"", "").replace(" ", "").split(",");

            String icao24 = plane[0];
            String callsign = plane[1];
            String origin_country = plane[2];
            int time_position = 0;
            if (!plane[3].equals("null")) {
                time_position = Integer.parseInt(plane[3]);
            }
            int last_contact = 0;
            if (!plane[4].equals("null")) {
                last_contact = Integer.parseInt(plane[4]);
            }
            double longitude = 999999;
            if (!plane[5].equals("null")) {
                longitude = Double.parseDouble(plane[5]);
            }
            double latitude = 999999;
            if (!plane[6].equals("null")) {
                latitude = Double.parseDouble(plane[6]);
            }
            boolean on_ground = Boolean.parseBoolean(plane[8]);
            double velocity = 0;
            if (!plane[9].equals("null")) {
                velocity = Double.parseDouble(plane[9]);
            }
            double true_track = 999999;
            if (!plane[10].equals("null")) {
                true_track = Double.parseDouble(plane[10]);
            }
            double vertical_rate = 0;
            if (!plane[11].equals("null")) {
                vertical_rate = Double.parseDouble(plane[11]);
            }
            double altitude = 0;
            if (!plane[13].equals("null")) {
                altitude = Double.parseDouble(plane[13]);
            }
            allplanes.save(new Plane(icao24, callsign, origin_country, time_position, last_contact, longitude, latitude, on_ground, velocity, true_track, vertical_rate, altitude));

            //allplanes.addPlane(new Plane(icao24, callsign, origin_country, time_position, last_contact, longitude, latitude, on_ground, velocity, true_track, vertical_rate, altitude));
        }
        logger.info("Planes' data saved in database.");
        producer.send(loggerTopicName, new Timestamp(new Date().getTime()) + " PlaneController: Planes' data saved in database.");
            
        return "Saved allPlanes";
    }

    private static final Logger logger = LoggerFactory.getLogger(PlaneController.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Value(value = "${message.topic.name}")
    private String loggerTopicName;
    
    @Scheduled(fixedDelay = 8000) //5 em 5 segundos
    public void reportCurrentTime() throws IOException {

        //logger.info("The time is now {}", dateFormat.format(new Date()));
        String url = "https://chiplo123:tareco123@opensky-network.org/api/states/all";

        MyGETRequest(url);
        //System.out.println(allplanes.findAll().get(0));//getOne("aa8c39").getIcao24() + ", "+ allplanes.getOne("aa8c39").getCallsign());
        producer.sendPlaneMessage(allplanes.findAll().get(0));
    }

    @Bean
    public Prod producer() {
        return new Prod();
    }

    @Bean
    public Cons consumer() {
        return new Cons();
    }

    //@PostMapping(value = "/publish")
    /*
    @GetMapping("/findAll")
    public Iterable<Plane> findAll() {
        return (Iterable<Plane>) allplanes.getAllPlanes();
    }

    @GetMapping(path = "/find/{icao24}")
    public Plane find(@PathVariable("icao24") String icao24) {
        return allplanes.getPlane(icao24);
    }

    @PostMapping(consumes = "application/json")
    public Plane create(@RequestBody Plane plane) {
        return allplanes.addPlane(plane);
    }

    @DeleteMapping(path = "/delete/{icao24}")
    public void delete(@PathVariable("icao24") String icao24) {
        allplanes.deletePlane(icao24);
    }

    @PutMapping(path = "/update/{icao24}")
    public Plane update(@PathVariable("icao24") String icao24, @RequestBody Plane plane) throws BadHttpRequest {
        if (allplanes.exists(icao24)) {
            plane.setIcao24(icao24);
            return plane;
        } else {
            throw new BadHttpRequest();
        }
    }*/
}
