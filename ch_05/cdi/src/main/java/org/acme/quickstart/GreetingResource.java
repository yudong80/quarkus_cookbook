// tag::base[]
package org.acme.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {
    @Inject                     // <1>
    GreetingService service;    // <2> 
    // end::base[]
    // tag::basic_method[]
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return service.getGreeting();
    }
    // end::basic_method[]
    // tag::locale_method[]
    @GET
    @Path("{locale}")
    public String sayHello(String locale) {
        return service.getGreeting(locale);
    }
    // end::locale_method[]
// tag:base[]
}
// end:base[]
