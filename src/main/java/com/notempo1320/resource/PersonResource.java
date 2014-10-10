package com.notempo1320.resource;

import com.notempo1320.facade.PersonFacade;
import com.notempo1320.model.Person;
import com.notempo1320.model.User;
import com.notempo1320.resource.exception.InternalErrorException;
import com.notempo1320.resource.exception.ResourceNotFoundException;
import io.dropwizard.auth.Auth;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;


@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource implements BaseResource<Person> {

    private final PersonFacade facade;

    @Inject
    public PersonResource(PersonFacade facade) {
        this.facade = facade;
    }

	@Override
    @POST
	public Response create(@Auth User user, Person model) {
		return null;
	}

	@Override
    @GET
	public List<Person> list(@Auth User user) throws InternalErrorException {
		return null;
	}

    @GET
	@Override
    @Path("/{id}")
	public Person retrieve(@Auth User user, @PathParam("id") UUID id) throws ResourceNotFoundException, InternalErrorException {
		return null;
	}

    @PUT
	@Override
    @Path("/{id}")
	public Person update(@Auth User user, @PathParam("id") UUID id, @Valid Person entity) throws ResourceNotFoundException, InternalErrorException {
		return null;
	}


    @DELETE
	@Override
    @Path("/{id}")
	public Response delete(@Auth User user, @PathParam("id") UUID id) throws ResourceNotFoundException, InternalErrorException {
		return null;
	}
}
