/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;
//host:8080/labproj/v1/resource

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
/**
 *
 * @author guilherme
 */
@ApplicationPath("")
public class AppConfig extends Application{
    private Set<Class<?>> resources=new HashSet<>();

    public AppConfig(){
        System.out.println("Created AppConfig");
        resources.add(R1.class);
    }
    
    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
