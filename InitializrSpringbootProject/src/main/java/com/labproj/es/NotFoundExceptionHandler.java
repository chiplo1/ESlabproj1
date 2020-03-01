/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.es;

/**
 *
 * @author guilherme
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<PlaneNotFound> {
    public Response toResponse(PlaneNotFound ex) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
