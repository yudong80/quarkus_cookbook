package org.acme.quickstart;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    // tag::message[]
    @ConfigProperty(name = "greeting.message") // <1>
    String message; // <2>

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return message; // <3>
    }
    // end::message[]
}