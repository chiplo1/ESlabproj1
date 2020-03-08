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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author joao
 */
@Path("/event")
@Produces("application/json")
public class EventResource{
    
    protected Connection getConnection() throws SQLException, NamingException {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource) ic.lookup("jdbc/DSTix");
        return ds.getConnection();
    }
    
    public Event getFromResultSet(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setIcao24(rs.getString("icao24"));
        event.setCallsign(rs.getString("callsign"));
        event.setOrigin_country(rs.getString("origin_country"));
        event.setTime_position(rs.getInt("time_position"));
        event.setLast_contact(rs.getInt("last_contact"));
        event.setLongitude(rs.getDouble("longitude"));
        event.setLatitude(rs.getDouble("latitude"));
        event.setOn_ground(rs.getBoolean("on_ground"));
        event.setVelocity(rs.getDouble("velocity"));
        event.setTrue_track(rs.getDouble("true_track"));
        event.setVertical_rate(rs.getDouble("vertical_rate"));
        event.setAltitude(rs.getDouble("altitude"));
        return event;
    }
    
    @GET
    public List getList() throws SQLException, NamingException {
        List events = new ArrayList<>();
        Connection db = getConnection();
        
        try {
            PreparedStatement st = db.prepareStatement("SELECT id, title, created from tix_event");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event e = getFromResultSet(rs);
                events.add(e);
            }
            return events;
        } finally {
            db.close();
        }
    }
}
