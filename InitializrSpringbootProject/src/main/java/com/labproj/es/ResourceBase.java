/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author joao
 */
@Produces("application/json")
public abstract class ResourceBase<T> {

    /* Methods declarations here */
    protected EntityManager getEntityManager() throws NamingException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tix");
        return emf.createEntityManager();
    }

    @GET
    public List<T> getList() throws SQLException, NamingException {
        List records = getTixQuery();
        return records;
    }

    @GET
    @Path("{id}")
    public List<T> getSingle(@PathParam("id") int id) throws NamingException {
        List records = getSingleQuery(id);
        return records;
    }

    @POST
    public void insertTix(T t) throws NamingException, SQLException {
        createQuery(t);
    }
}
