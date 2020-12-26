// tag::base[]
package org.acme.quickstart;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/developer")
@RegisterRestClient
@Consumes("application/json") // <1>
@Produces("application/json")
public interface DeveloperService {


  @HEAD // <2>
  Response head();

  @GET
  List<Developer> getDevelopers();

  @POST // <3>
  Response createDeveloper(
      @HeaderParam("Authorization") String authorization, // <4>
      Developer developer); // <5>

  @DELETE // <6>
  @Path("/{userId}")
  Response deleteUser(@CookieParam("AuthToken") String authorization, // <7>
      @PathParam("developerId") Long developerId);                                         
// end::base[]
      // tag::put[]
      @PUT
      @Path("/{developerId}")
      Response updateUser(@BeanParam PutDeveloper putDeveloper, // <1>
          Developer developer);
      // end::put[] 
// tag::base[]
}
// end::base[]
