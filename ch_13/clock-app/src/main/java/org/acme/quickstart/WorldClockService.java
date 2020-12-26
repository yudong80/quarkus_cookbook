package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api") // <1>
@ApplicationScoped
@RegisterRestClient // <2>
public interface WorldClockService {

    @GET // <3>
    @Path("/json/{timezone}/now") // <4>
    @Produces(MediaType.APPLICATION_JSON) // <5>
    WorldClock getNow(@PathParam("timezone") String timezone); // <6>
    
}