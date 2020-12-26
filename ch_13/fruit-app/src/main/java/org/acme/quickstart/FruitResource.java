package org.acme.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/fruit")
public class FruitResource {

    @RestClient
    FruityViceService fruityViceService;

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public FruityVice getFruitInfoByName(@PathParam("name") String name) {
        return fruityViceService.getFruitByName(name);
    }
}