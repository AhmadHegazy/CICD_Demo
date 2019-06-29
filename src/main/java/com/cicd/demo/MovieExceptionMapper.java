package com.cicd.demo;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MovieExceptionMapper implements ExceptionMapper<Throwable>{
	@Override
	public Response toResponse(Throwable e) {
		return Response.serverError().entity(e.getMessage()).build();
	}
}
