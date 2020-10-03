// tag::basic[]
package org.acme.quickstart;
// end::basic[]

// tag::crud-imports[]
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
// end::crud-imports[]
// tag::basic[]
import javax.ws.rs.GET;
// end::basic[]
// tag::crud-imports[]
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
// end::crud-imports[]
// tag::basic[]
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello") // <1>
public class GreetingResource { 
// end::basic[]
    // tag::log[]
    private static org.jboss.logging.Logger logger = 
                    org.jboss.logging.Logger.getLogger(GreetingResource.class); // <1>

    @GET
    @Path("/log") // <2>
    @Produces(MediaType.TEXT_PLAIN)
    public String helloLog() {
        logger.info("I said Hello"); // <3>
        return "hello";
    }
    // end::log[]

// tag::basic[]
    @GET // <2>
    @Produces(MediaType.TEXT_PLAIN) // <3>
    public String hello() {
        return "hello"; // <4>
    }
// end::basic[]

    // tag::crud[]
    @POST // <1>
    @Consumes(MediaType.TEXT_PLAIN) // <2>
    public void create(String message) { // <3>
        System.out.println("Create");
    }

    @PUT // <4>
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String message) {
        System.out.println("Update");
        return message;
    }

    @DELETE // <5>
    public void delete() {
        System.out.println("Delete");
    }
    // end::crud[] 
// tag::basic[]
}
// end::basic[]
