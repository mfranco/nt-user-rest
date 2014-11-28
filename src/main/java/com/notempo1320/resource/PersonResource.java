package com.notempo1320.resource;

import com.google.common.base.Optional;
import com.notempo1320.configuration.AppConfiguration;
import com.notempo1320.facade.BaseFacade;
import com.notempo1320.model.Person;
import com.notempo1320.model.SerializedModel;
import com.notempo1320.model.User;
import com.notempo1320.resource.exception.InternalErrorException;
import com.notempo1320.resource.exception.ResourceNotFoundException;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/persons")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource implements BaseResource<Person> {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(PersonResource.class);

    private final BaseFacade<Person> facade;
    private final URI resourceUri;
    private final AppConfiguration config;

    @Inject
    public PersonResource(BaseFacade<Person> facade, AppConfiguration config)
        throws java.net.URISyntaxException {
        this.resourceUri = config.getEndpoint().resolve("/persons");
        this.facade = facade;
        this.config = config;
    }

	@Override
    @POST
    @UnitOfWork
	public Response create(@Auth User user, @Valid Person model,
        @Context UriInfo info) {

        LOGGER.info("Creating person with admin user {}", user.getUsername());
        model.setActive(true);
        Person obj = facade.create(model);
        URI resource = HttpUtils.getCreatedResourceURI(info,
            resourceUri, obj.getId());

        LOGGER.info("Person with id {} created", obj.getId());
        return Response.created(resource).entity(
            new SerializedModel<>("person", obj)).build();
	}

	@Override
    @GET
    @UnitOfWork
	public List<Person> list(@Auth User user) throws InternalErrorException {
        LOGGER.info("Getting person list with admin user {}",
            user.getUsername());
        return facade.findByParams(Optional.fromNullable(null));
	}

    @GET
	@Override
    @Path("/{id}")
    @UnitOfWork
	public Person retrieve(@Auth User user, @PathParam("id") Long id)
        throws ResourceNotFoundException, InternalErrorException {

        LOGGER.info("Retreiving person {} with admin user {}", id,
            user.getUsername());

        Optional<Person> op = facade.findById(id);
        if (!op.isPresent()) {
			throw new ResourceNotFoundException();
		}
		return op.get();

	}

    @PUT
	@Override
    @Path("/{id}")
    @UnitOfWork
	public Person update(@Auth User user, @PathParam("id") Long id,
        @Valid Person model) throws ResourceNotFoundException,
        InternalErrorException {

        LOGGER.info("Updating person {} with admin user {}", id,
            user.getUsername());

        Optional<Person> op = facade.findById(id);

        if (!op.isPresent()) {
			throw new ResourceNotFoundException();
		}

        return facade.update(model);
	}


    @DELETE
	@Override
    @Path("/{id}")
    @UnitOfWork
	public Response delete(@Auth User user, @PathParam("id") Long id)
        throws ResourceNotFoundException, InternalErrorException {

        LOGGER.info("Deleteing person {} with admin user {}", id,
            user.getUsername());
        Optional<Person> op = facade.findById(id);
        if (!op.isPresent()) {
			throw new ResourceNotFoundException();
		}

        Person obj = op.get();
        facade.delete(obj);
        return Response.ok().build();
	}
}
