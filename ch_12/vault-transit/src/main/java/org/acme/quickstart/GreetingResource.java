package org.acme.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.quarkus.vault.VaultTransitSecretEngine;

@Path("/hello")
public class GreetingResource {

    // tag::method[]
    @Inject
    VaultTransitSecretEngine transit; // <1>

    @GET
    @Path("/encrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String encrypt(@QueryParam("text") String text) {
        return transit.encrypt("my_encryption", text); // <2>
    }

    @GET
    @Path("/decrypt")
    @Produces(MediaType.TEXT_PLAIN)
    public String decrypt(@QueryParam("text") String text) {
        return transit.decrypt("my_encryption", text).asString(); // <3>
    }

    @GET
    @Path("/sign")
    @Produces(MediaType.TEXT_PLAIN)
    public String sign(@QueryParam("text") String text) {
        return transit.sign("my-sign-key", text); // <4>
    }
    // end::method[]
}