package org.acme.scheduling;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/count")
public class CountResource {

    @Inject
    Scheduler scheduler;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello, count: " + scheduler.get();
    }
}
