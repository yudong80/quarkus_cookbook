package org.acme.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    // tag::configmap[]
    @ConfigProperty(name = "greeting") // <1>
    String greeting;

    @ConfigProperty(name = "some.property1") // <2>
    String property1;

    @ConfigProperty(name = "some.property2")
    String property2;
    // end::configmap[]

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeting;
    }

    @GET
    @Path("/p1")
    @Produces(MediaType.TEXT_PLAIN)
    public String prop1() {
        return property1;
    }

    @GET
    @Path("/p2")
    @Produces(MediaType.TEXT_PLAIN)
    public String prop2() {
        return property2;
    }

}