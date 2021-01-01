package org.acme.quickstart;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/developer")
public class GreetingResource {

    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public List<Developer> developers() {
        return Developer.findAll().list();
    }
}