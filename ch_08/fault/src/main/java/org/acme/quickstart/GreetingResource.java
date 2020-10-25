package org.acme.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.faulttolerance.exceptions.TimeoutException;

@Path("/hello")
public class GreetingResource {

    @Inject
    ServiceInvoker serviceInvoker;

    @GET
    @Path("/retry")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloRetryFallback() {
        return serviceInvoker.getHelloWithFallback();
    }

    @GET
    @Path("/timeout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloTimeout() {
        try {
        return Response.ok(serviceInvoker.getHelloWithTimeout()).build();
        } catch(TimeoutException e) {
            return Response.serverError().entity("Timeout").build();
        }
    }

    @GET
    @Path("/bulkhead")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloBulkhead() {
        return serviceInvoker.getHelloBulkhead();
    }

    @GET
    @Path("/circuitbreaker")
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloCiruitBreaker() {
        try {
            return Response.ok(serviceInvoker.getHelloCircuitBreaker()).build();
        } catch(IllegalStateException e) {
            return Response.serverError().entity("Normal Exception").build();
        } catch(CircuitBreakerOpenException e) {
            return Response.serverError().entity("Circuit Open").build();
        }
    }

}