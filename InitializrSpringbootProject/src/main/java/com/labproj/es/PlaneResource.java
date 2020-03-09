/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
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
@Path("/plane")
@Produces("application/json")
public class PlaneResource { //NOT BEING USED
    @Autowired
    private PlaneRepository planeRepository;
    
    
    protected Connection getConnection() throws SQLException, NamingException {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource) ic.lookup("jdbc/mysql");
        return ds.getConnection();
    }
    
    public Plane getFromResultSet(ResultSet rs) throws SQLException {
        Plane plane = new Plane();
        plane.setIcao24(rs.getString("icao24"));
        plane.setCallsign(rs.getString("callsign"));
        plane.setOrigin_country(rs.getString("origin_country"));
        plane.setTime_position(rs.getInt("time_position"));
        plane.setLast_contact(rs.getInt("last_contact"));
        plane.setLongitude(rs.getDouble("longitude"));
        plane.setLatitude(rs.getDouble("latitude"));
        plane.setOn_ground(rs.getBoolean("on_ground"));
        plane.setVelocity(rs.getDouble("velocity"));
        plane.setTrue_track(rs.getDouble("true_track"));
        plane.setVertical_rate(rs.getDouble("vertical_rate"));
        plane.setAltitude(rs.getDouble("altitude"));
        return plane;
    }
    
    @GET
    public List getList() throws SQLException, NamingException {
        List planes = new ArrayList<>();
        Connection db = getConnection();
        
        try {
            PreparedStatement st = db.prepareStatement("SELECT * from plane");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Plane e = getFromResultSet(rs);
                planes.add(e);
            }
            return planes;
        } finally {
            db.close();
        }
    }
    
    
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
