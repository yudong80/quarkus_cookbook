package org.acme.quickstart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    // tag::token[]
    @ConfigProperty(name = "github.api.key.token")
    String githubToken;
    // end::token[]

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ghToken() {
        return githubToken;
    }

    // tag::tokenfile[]
    @GET
    @Path("/file")
    @Produces(MediaType.TEXT_PLAIN)
    public String ghTokenFile() throws IOException {
        final byte[] encodedGHToken = Files.readAllBytes(
            Paths.get("/deployment/github/github.api.key.token")); // <1>
        return new String(encodedGHToken);
    }
    // end::tokenfile[]
}
