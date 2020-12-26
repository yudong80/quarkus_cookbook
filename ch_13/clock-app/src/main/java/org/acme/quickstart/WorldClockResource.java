// tag::base[]
package org.acme.quickstart;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/now")
public class WorldClockResource {

  @ConfigProperty(name = "clock.host", 
  defaultValue = "http://worldclockapi.com")
    String clockHost; // <1>

  private Client client = ClientBuilder.newClient(); // <2>

  @GET
  @Path("{timezone}")
  @Produces(MediaType.APPLICATION_JSON)
  public WorldClock getCurrentTime(@PathParam("timezone") String timezone) {
    WorldClock worldClock = client.target(clockHost) // <3>
      .path("api/json/{timezone}/now") // <4>
      .resolveTemplate("timezone", timezone) // <5>
      .request(MediaType.APPLICATION_JSON)
      .get(WorldClock.class); // <6> <7>

    return worldClock;
  }
// end::base[] 
  // tag::response[]
  @GET
  @Path("{timezone}/raw")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCurrentTimeResponse(@PathParam("timezone") 
      String timezone) {
    javax.ws.rs.core.Response responseWorldClock = client.target(clockHost)
      .path("api/json/{timezone}/now")
      .resolveTemplate("timezone", timezone)
      .request(MediaType.APPLICATION_JSON)
      .get(Response.class);

    System.out.println(responseWorldClock.getStatus());
    System.out.println(responseWorldClock.getStringHeaders());
    // ... more methods

    return responseWorldClock;
  }
  // end::response[] 
  // tag::mp[]
  @RestClient // <1>
  WorldClockService worldClockService;

  @GET
  @Path("{timezone}/mp")
  @Produces(MediaType.APPLICATION_JSON)
  public WorldClock getCurrentTimeMp(@PathParam("timezone") String timezone) {
    return worldClockService.getNow(timezone); // <2>
  }
  // end::mp[] 
// tag::base[]
}
// end::base[]
