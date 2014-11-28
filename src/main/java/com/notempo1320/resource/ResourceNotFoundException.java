package com.notempo1320.resource;;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


class ResourceNotFoundException extends WebApplicationException {

	public ResourceNotFoundException() {
		super(Response.Status.NOT_FOUND);
	}
}
