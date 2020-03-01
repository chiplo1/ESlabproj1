/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author guilherme
 */
@Path("/planes")
public class PlaneResource {
    @Autowired
    private PlaneRepository planeRepository;
 
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Plane getPlane(@PathParam("id") String icao24) {
        return planeRepository.getPlane(icao24);
    }
 
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response addPlane(
      Plane plane, @Context UriInfo uriInfo) {
  
        planeRepository.addPlane(new Plane(plane.getIcao24(), 
          plane.getCallsign()));
  
        return Response.status(Response.Status.CREATED.getStatusCode())
          .header(
            "Location", 
            String.format("%s/%s",uriInfo.getAbsolutePath().toString(), 
            plane.getIcao24())).build();
    }
}
