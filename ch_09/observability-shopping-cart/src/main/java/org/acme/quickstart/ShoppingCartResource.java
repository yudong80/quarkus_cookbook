package org.acme.quickstart;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.opentracing.Tracer;


@Path("/shop")
public class ShoppingCartResource {

    // tag::custom_tracer[]
    @Inject // <1>
    Tracer tracer;
    // end::custom_tracer[]

    // tag::custom_tracer[]
    @POST
    @Path("/add/{customerId}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(@PathParam("customerId") String customerId, Item item) {

        if (customerId.startsWith("1")) {
            tracer.activeSpan().setTag("important.customer", true); // <2>
        }
        // end::custom_tracer[]

        Optional<Cart> cart = Optional.ofNullable(Cart.find("customerId", customerId).firstResult());

        Cart currentCart = cart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.customerId = customerId;
            return newCart;
        });

        currentCart.addItem(item);
        currentCart.persist();
        return Response.ok().build();
    // tag::custom_tracer[]
    }
    // end::custom_tracer[]
}