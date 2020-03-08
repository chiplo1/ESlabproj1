/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guilherme
 */
@XmlRootElement
@Entity
@Table(name="tix_event")
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private String icao24;
    private String callsign;
    private String origin_country;
    private int time_position;
    private int last_contact;
    private double longitude;
    private double latitude;
    private boolean on_ground;
    private double velocity;
    private double true_track;
    private double vertical_rate;
    private double altitude;

    public Plane(String icao24, String callsign, String origin_country, int time_position, int last_contact, double longitude, double latitude, boolean on_ground, double velocity, double true_track, double vertical_rate, double altitude) {
        this.icao24 = icao24;
        this.callsign = callsign;
        this.origin_country = origin_country;
        this.time_position = time_position;
        this.last_contact = last_contact;
        this.longitude = longitude;
        this.latitude = latitude;
        this.on_ground = on_ground;
        this.velocity = velocity;
        this.true_track = true_track;
        this.vertical_rate = vertical_rate;
        this.altitude = altitude;
    }

    public Plane(String icao24, String callsign) {
        this.icao24 = icao24;
        this.callsign = callsign;
    }
    
    public Plane(String icao24) {
        this.icao24 = icao24;
    }
    
    public String getIcao24() {
        return icao24;
    }

    public void setIcao24(String icao24) {
        this.icao24 = icao24;
    }

    public String getCallsign() {
        return callsign;
    }

    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public int getTime_position() {
        return time_position;
    }

    public void setTime_position(int time_position) {
        this.time_position = time_position;
    }

    public int getLast_contact() {
        return last_contact;
    }

    public void setLast_contact(int last_contact) {
        this.last_contact = last_contact;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isOn_ground() {
        return on_ground;
    }

    public void setOn_ground(boolean on_ground) {
        this.on_ground = on_ground;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getTrue_track() {
        return true_track;
    }

    public void setTrue_track(double true_track) {
        this.true_track = true_track;
    }

    public double getVertical_rate() {
        return vertical_rate;
    }

    public void setVertical_rate(double vertical_rate) {
        this.vertical_rate = vertical_rate;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    

    
    
    
    public List<Plane> getPlanes(){
        List<Plane> planes = new ArrayList<>();
        
        planes.add(new Plane("alberto","caeiro"));
        planes.add(new Plane("fernando1","pessoa"));
        planes.add(new Plane("fernando2","humano"));
        
        return planes;
    }
    
}
