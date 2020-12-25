// tag::base[]
package org.acme.quickstart;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/hello")
@RequestScoped // <1>
public class GreetingResource {

    @Inject
    JsonWebToken callerPrincipal; // <2>

    @Claim(standard = Claims.preferred_username) // <3>
    String username;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
// end::base[]
    // tag::roles[]
    @RolesAllowed("Tester")
    // end::roles[]
// tag::base[]
    public String hello() {
        return "hello " + username;
    }
}
// end::base[]
