/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.labproj.exceptions;

/**
 *
 * @author guilherme
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyExistsExceptionHandler implements ExceptionMapper<PlaneAlreadyExists> {
    public Response toResponse(PlaneAlreadyExists ex) {
        return Response.status(Response.Status.CONFLICT.getStatusCode()).build();
    }
}
