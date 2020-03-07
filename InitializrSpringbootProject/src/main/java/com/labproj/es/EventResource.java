package com.labproj.es;

import java.sql.SQLException;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author joao
 */
@Path("/event")
@Produces("application/json")
public class EventResource extends ResourceBase<Event> {

    private EntityManager em;
    private List<Event> listEvents;

    @Override
    protected List<Event> getTixQuery() throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        listEvents = em.createQuery("SELECT e FROM Event e").getResultList();
        em.getTransaction().commit();
        em.close();
        return listEvents;
    }

    @Override
    protected List getSingleQuery(int id) throws NamingException {
        em = getEntityManager();
        em.getTransaction().begin();
        listEvents = singletonList(em.find(Event.class, id));
        em.getTransaction().commit();
        em.close();
        return listEvents;
    }

    @Override
    protected void createQuery(Event t) throws SQLException, NamingException {
        Event event = new Event();
        em = getEntityManager();
        em.getTransaction().begin();
        event.setIcao24(t.getIcao24());
        event.setAltitude(t.getAltitude());
        event.setCallsign(t.getCallsign());
        event.setLast_contact(t.getLast_contact());
        event.setLatitude(t.getLatitude());
        event.setLongitude(t.getLongitude());
        event.setOn_ground(t.isOn_ground());
        event.setOrigin_country(t.getOrigin_country());
        event.setTime_position(t.getTime_position());
        event.setTrue_track(t.getTrue_track());
        event.setVelocity(t.getVelocity());
        event.setVertical_rate(t.getVertical_rate());
        em.persist(event);
        em.getTransaction().commit();
        em.close();
    }
