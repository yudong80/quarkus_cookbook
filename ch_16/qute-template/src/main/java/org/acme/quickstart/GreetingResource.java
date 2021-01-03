package org.acme.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.TemplateInstance;


@Path("/hello")
public class GreetingResource {

    // tag::method[]
    @Inject
    io.quarkus.qute.Template hello; // <1> <2>

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public TemplateInstance hello() { // <3>
        final String name = "Alex";
        return hello.data("name", name); // <4>
    }
    // end::method[]
}