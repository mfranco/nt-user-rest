package com.notempo1320.resource.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class InternalErrorException  extends WebApplicationException {
	public InternalErrorException(){
		super(Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
	}
}
