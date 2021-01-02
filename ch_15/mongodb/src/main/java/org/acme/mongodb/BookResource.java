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

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    @Inject
    BookService service;

    @GET
    public List<Book> getAll() {
        return service.list();
    }

    @GET
    @Path("{isbn}")
    public Book getSingle(@PathParam("isbn") String isbn) {
        return service.findSingle(isbn);
    }

    @POST
    public Response add(Book b) {
        service.add(b);
        return Response.status(Response.Status.CREATED)
                .entity(service.list()).build();
    }
}
