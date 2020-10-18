package org.acme.mongodb.panache;

import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
    @GET
    public List<Book> getAll() {
        return Book.listAll();
    }

    @GET
    @Path("{isbn}")
    public Book getSingle(@PathParam("isbn") String isbn) {
        return Book.findByIsbn(isbn);
    }

    @POST
    public Response add(Book book) {
        book.persist();
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

    @DELETE
    @Path("{isbn}")
    public Response deleteByIsbn(@PathParam("isbn") String isbn) {
        Book.findByIsbn(isbn).delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
