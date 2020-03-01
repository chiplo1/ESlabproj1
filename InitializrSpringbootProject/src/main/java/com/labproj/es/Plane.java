/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

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
    private int Age;

    public Plane(int id, String firstName, String lastName, int Age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.Age = Age;
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
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
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
    
    
}
