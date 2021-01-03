package org.acme.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingProducer greetingProducer;

    // tag::method[]
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        long initial = System.currentTimeMillis();
        String msg = greetingProducer.getMessage(); // <1>
        long end = System.currentTimeMillis();
        return msg + " " + (end - initial) + "ms";
    }
    // end::method[]
}