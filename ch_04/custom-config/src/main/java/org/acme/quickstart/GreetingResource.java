package org.acme.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    // tag::inmem[]
    @ConfigProperty(name = "greeting.color") // <1>
    String color;

    @GET
    @Path("/color")
    @Produces(MediaType.TEXT_PLAIN)
    public String color() {
        return color;
    }
    // end::inmem[]

    // tag::converter[]
    @ConfigProperty(name = "greeting.vat")
    Percentage vat;

    @GET
    @Path("/vat")
    @Produces(MediaType.TEXT_PLAIN)
    public String vat() {
        return Double.toString(vat.getPercentage());
    }
    // end::converter[]

}