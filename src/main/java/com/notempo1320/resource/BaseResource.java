package com.notempo1320.resource;

import com.notempo1320.model.BaseModel;
import com.notempo1320.model.User;
import com.notempo1320.resource.exception.InternalErrorException;
import com.notempo1320.resource.exception.ResourceNotFoundException;
import io.dropwizard.auth.Auth;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BaseResource <T extends BaseModel> {

	@POST
	public Response create(@Auth User user, T model);

	@GET
	public List<T> list(@Auth User user) throws InternalErrorException;

    @GET
	@Path("/{id}")
	public T retrieve(@Auth User user, @PathParam("id") UUID id) throws ResourceNotFoundException,
			InternalErrorException;

	@PUT
	@Path("/{id}")
	public T update(@Auth User user, @PathParam("id") UUID id, @Valid T entity) throws ResourceNotFoundException,
			InternalErrorException;

	@DELETE
	@Path("/{id}")
	public Response delete(@Auth User user, @PathParam("id") UUID id) throws ResourceNotFoundException, InternalErrorException;
}
