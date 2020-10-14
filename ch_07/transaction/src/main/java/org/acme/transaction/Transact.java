// tag::base[]
package org.acme.transaction;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/tx")
@Transactional
public class Transact {
// end::base[]
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    // tag::no-tx[]
    @Inject
    NoTransact noTx;

    @GET
    @Path("/no")
    @Produces(MediaType.TEXT_PLAIN)
    public String hi() {
        return noTx.word();
    }
    // end::no-tx[]
// tag::base[]
}
// end::base[]
