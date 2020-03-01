/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guilherme
 */
@XmlRootElement
public class Plane {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public Plane(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    
    public Plane(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }
    
    public Plane(int id) {
        this.id = id;
    }
    
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public List<Plane> getPlanes(){
        List<Plane> planes = new ArrayList<>();
        
        planes.add(new Plane(1,"alberto","caeiro",19));
        planes.add(new Plane(2,"fernando","pessoa",123));
        planes.add(new Plane(3,"fernando","humano",1543));
        
        return planes;
    }
    
}
