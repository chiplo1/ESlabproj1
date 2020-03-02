/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import net.minidev.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PlaneController {

    
    private Plane plane = new Plane("4");
    
    private static PlaneRepositoryImpl allplanes = new PlaneRepositoryImpl();
    
    //@Scheduled(fixedRate = 1000)
    @RequestMapping(value = "/plane")
    public ModelAndView getBlog(ModelAndView mv) {
        mv.addObject("planes", plane.getPlanes());
        mv.setViewName("index");
        System.out.println("PRINTING EHHEHE");
        return mv;
    }
    
    
    //@Scheduled(fixedRate = 1000)
    @RequestMapping("/allplanes")
    public ModelAndView allPlanes(ModelAndView mv) throws IOException {

        //curl -X POST https://postman-echo.com/post --data foo1=bar1&foo2=bar2
        String url = "https://opensky-network.org/api/states/all";
        //String command = "curl -s 'https://opensky-network.org/api/states/all?icao24=3c6444&icao24=3e1bf9'";
        MyGETRequest(url);
        
        
        mv.addObject("planes", allplanes.getAllPlanes());
        mv.setViewName("allPlanes");
        
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
            String[] planes = rawResponse.split("\\[\"");
            for(int i=1;i<planes.length;i++){
                //System.out.println(planes[i]);
                String[] plane = planes[i].replace("\"", "").replace(" ", "").split(",");
                for(int j=0;j<plane.length;j++){
                    //System.out.println(plane[j]);
                    //Plane(String icao24, String callsign, String origin_country, int time_position, int last_contact, double longitude, double latitude, boolean on_ground, double velocity, double true_track, double vertical_rate, double altitude)
                }
                String icao24 = plane[0];
                String callsign = plane[1];
                String origin_country = plane[2];
                int time_position = 0;
                if(!plane[3].equals("null")){
                    time_position = Integer.parseInt(plane[3]);
                }
                int last_contact = 0;
                if(!plane[4].equals("null")){
                    last_contact = Integer.parseInt(plane[4]);
                }
                double longitude = 999999;
                if(!plane[5].equals("null")){
                    longitude = Double.parseDouble(plane[5]);
                }
                double latitude = 999999;
                if(!plane[6].equals("null")){
                    latitude = Double.parseDouble(plane[6]);
                }
                boolean on_ground = Boolean.parseBoolean(plane[8]);
                double velocity = 999999;
                if(!plane[9].equals("null")){
                    velocity = Double.parseDouble(plane[9]);
                }
                double true_track = 999999;
                if(!plane[10].equals("null")){
                    true_track = Double.parseDouble(plane[10]);
                }
                double vertical_rate = 999999;
                if(!plane[11].equals("null")){
                    vertical_rate = Double.parseDouble(plane[11]);
                }
                double altitude = 999999;
                if(!plane[13].equals("null")){
                    altitude = Double.parseDouble(plane[13]);
                }
                allplanes.addPlane(new Plane(icao24,callsign,origin_country,time_position,last_contact,longitude,latitude,on_ground,velocity,true_track,vertical_rate,altitude));
            }
            
            return rawResponse;
        } else {
            System.out.println("GET NOT WORKED");
        }
        return "GET NOT WORKED";
    }
}
