/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author guilherme
 */
@Path("r2")
public class R2 {
    
    @GET
    public String hello(){
        return "Another Rest Resource!";
    }
}
