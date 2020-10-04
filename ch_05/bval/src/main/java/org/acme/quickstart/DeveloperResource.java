package org.acme.quickstart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/developer")
public class DeveloperResource {

    private static final List<Developer> developers = new ArrayList<>();

    // tag::programmatic[]
    @Inject
    Validator validator; // <1>

    @POST
    @Path("/programmaticvalidation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProgrammaticValidation(Developer developer) { //<2>
        Set<ConstraintViolation<Developer>> violations = 
          validator.validate(developer); // <3>
        
        if (violations.isEmpty()) { // <4>
            developers.add(developer);
            return Response.ok().build();
        } else {
            JsonArrayBuilder errors = Json.createArrayBuilder();
            for (ConstraintViolation<Developer> violation : violations) { //<5>
                errors.add(
                    Json.createObjectBuilder()
                    .add("path", violation.getPropertyPath().toString())
                    .add("message", violation.getMessage())
                    );
            }

            return Response.status(Response.Status.BAD_REQUEST)
                           .entity(errors.build())
                           .build();
        }
    }
    // end::programmatic[]

    // tag::validation[]
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDeveloper(@Valid Developer developer) { // <1>
        developers.add(developer);
        return Response.ok().build();
    }
    // end::validation[]

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public @Valid List<Developer> getDevelopers() {
        return developers;
    }
}
