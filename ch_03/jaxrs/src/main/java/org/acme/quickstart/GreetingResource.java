package org.acme.quickstart;


import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/hello")
public class GreetingResource {

    // tag::extract[]
    public static enum Order {
        desc, asc;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(
                    @Context UriInfo uriInfo, // <1>
                    @QueryParam("order") Order order, // <2>
                    @NotBlank @HeaderParam("authorization") String authorization // <3>
                    ) {


        return String.format("URI: %s - Order %s - Authorization: %s",
                             uriInfo.getAbsolutePath(), order, authorization);
    }
    // end::extract[]

    // tag::lock[]
    @LOCK // <1>
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String lockResource(@PathParam("id") long id) {
        return id + " locked";
    }
    // end::lock[]

}
