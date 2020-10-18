package org.acme.dynamodb;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookSyncService service;

    @GET
    public List<Book> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{isbn}")
    public Book getSingleBook(@PathParam("isbn") String isbn) {
        return service.get(isbn);
    }

    @POST
    public List<Book> add(Book b) {
        return service.add(b);
    }
}
