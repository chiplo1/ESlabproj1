/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es.config;

/**
 *
 * @author guilherme
 */
import com.labproj.exceptions.AlreadyExistsExceptionHandler;
import com.labproj.exceptions.NotFoundExceptionHandler;
import com.labproj.es.PlaneResource;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources")
public class RestConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(PlaneResource.class, NotFoundExceptionHandler.class, AlreadyExistsExceptionHandler.class));
    }
}
