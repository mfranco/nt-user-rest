package com.notempo1320.resource.exception;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ResourceNotFoundException extends WebApplicationException {

	public ResourceNotFoundException() {
		super(Response.status(Response.Status.NOT_FOUND).build());
	}
}
