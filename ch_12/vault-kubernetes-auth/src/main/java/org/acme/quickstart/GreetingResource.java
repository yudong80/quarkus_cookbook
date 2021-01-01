package org.acme.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {
    // tag::method[]
    @ConfigProperty(name = "foo")
    String foo;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return foo;
    }
    // end::method[]
}