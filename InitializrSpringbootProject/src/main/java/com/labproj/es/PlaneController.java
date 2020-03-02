/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import com.labproj.exceptions.PlaneNotFound;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@EnableScheduling
public class PlaneController {

    PlaneRepositoryImpl allplanes;

    public PlaneController() {

        allplanes = new PlaneRepositoryImpl();
    }

    //@Scheduled(fixedRate = 1000)
    @RequestMapping(value = "/plane")
    public ModelAndView getBlog(ModelAndView mv) {
        //mv.addObject("planes", plane.getPlanes());
        mv.setViewName("index");
        System.out.println("PRINTING EHHEHE");
        return mv;
    }

    public PlaneRepositoryImpl getAllplanes() {
        return allplanes;
    }

    //@Scheduled(fixedRate = 1000)
    @RequestMapping("/allplanes")
    public ModelAndView allPlanes(ModelAndView mv) throws IOException {

        //curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2
        String url = "https://chiplo123:tareco123@opensky-network.org/api/states/all";
        //String command = "curl -s 'https://opensky-network.org/api/states/all?icao24=3c6444&icao24=3e1bf9'";
        
        
        System.out.println("CARREGUEI NO F5");
        try{
            System.out.println(allplanes.getPlane("ab1644").getLatitude());
        }
        catch(PlaneNotFound e){
            System.out.println("Plane not found");
        }
        mv.addObject("planes", allplanes.getAllPlanes());
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
            processPlanes(rawResponse);
            
            return rawResponse;
        } else {
            System.out.println("GET NOT WORKED");
        }
        return "GET NOT WORKED";
    }

    public void setAllplanes(PlaneRepositoryImpl allplanes) {
        this.allplanes = allplanes;
    }

    public PlaneRepositoryImpl processPlanes(String rawResponse) {
        
        allplanes.deleteAllPlanes(); 
        
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
            allplanes.addPlane(new Plane(icao24, callsign, origin_country, time_position, last_contact, longitude, latitude, on_ground, velocity, true_track, vertical_rate, altitude));
        }
        return allplanes;
    }
    
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    @Scheduled(fixedDelay = 8000) //5 em 5 segundos
    public void reportCurrentTime() throws IOException {

    	log.info("The time is now {}", dateFormat.format(new Date()));
        String url = "https://chiplo123:tareco123@opensky-network.org/api/states/all";
        
        MyGETRequest(url);
        
    }
}
