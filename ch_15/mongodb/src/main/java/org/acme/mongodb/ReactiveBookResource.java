package org.acme.mongodb;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.mutiny.Uni;

@Path("/reactive_books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveBookResource {
    @Inject
    ReactiveBookService service;

    @GET
    public Uni<List<Book>> getAll() {
        return service.list();
    }

    @GET
    @Path("{isbn}")
    public Uni<Book> getSingle(@PathParam("isbn") String isbn) {
        return service.findSingle(isbn);
    }

    @POST
    public Uni<Response> add(Book b) {
        return service.add(b).onItem().ignore()
                .andSwitchTo(this::getAll)
                .map(books -> Response.status(Response.Status.CREATED)
                                      .entity(books).build());
    }
}
