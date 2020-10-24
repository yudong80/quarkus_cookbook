package org.acme.neo4j;

import java.net.URI;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Values;
import org.neo4j.driver.async.AsyncSession;
import org.neo4j.driver.async.ResultCursor;
import org.neo4j.driver.exceptions.NoSuchRecordException;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    @Inject
    Driver driver;

    // tag::getAll[]
    @GET
    public CompletionStage<Response> getAll() {
        AsyncSession session = driver.asyncSession();   // <1>

        return session
                .runAsync("MATCH (b:Book) RETURN b ORDER BY b.title")   // <2>
                .thenCompose(cursor -> cursor.listAsync(record ->
                        Book.from(record.get("b").asNode()))) // <3>
                .thenCompose(books -> session.
                        closeAsync().thenApply(signal -> books))  // <4>
                .thenApply(Response::ok)    // <5>
                .thenApply(Response.ResponseBuilder::build);
    }
    // end::getAll[]
    // tag::getSingle[]
    @GET
    @Path("{id}")
    public CompletionStage<Response> getById(@PathParam("id") Long id) {
        AsyncSession session = driver.asyncSession();
        return session.readTransactionAsync(tx ->
                tx.runAsync("MATCH (b:Book) WHERE id(b) = $id RETURN b",
                                    Values.parameters("id", id))
                        .thenCompose(ResultCursor::singleAsync))
                .handle(((record, err) -> {
                    if (err != null) {
                        Throwable source = err;
                        if (err instanceof CompletionException)
                            source = ((CompletionException) err).getCause();
                        Response.Status status = Response.Status.
                                                    INTERNAL_SERVER_ERROR;
                        if (source instanceof NoSuchRecordException)
                            status = Response.Status.NOT_FOUND;

                        return Response.status(status).build();
                    } else {
                        return Response.ok(Book.from(record.get("b")
                                            .asNode())).build();
                    }
                }))
                .thenCompose(response -> session.closeAsync()
                                                .thenApply(signal -> response));
    }
    // end::getSingle[]
    // tag::create[]
    @POST
    public CompletionStage<Response> create(Book b) {
        AsyncSession session = driver.asyncSession();
        return session
                .writeTransactionAsync(tx ->
                        {
                            String query = "CREATE (b:Book " +
                            "{title: $title, isbn: $isbn, authors: $authors})" +
                            " RETURN b";
                            return tx.runAsync(query,
                                    Values.parameters("title", b.title,
                                            "isbn", b.isbn,
                                            "authors", b.authors))
                                    .thenCompose(ResultCursor::singleAsync);
                        }
                )
                .thenApply(record -> Book.from(record.get("b").asNode()))
                .thenCompose(persistedBook -> session.closeAsync()
                        .thenApply(signal -> persistedBook))
                .thenApply(persistedBook -> Response.created(
                        URI.create("/book/" + persistedBook.id)).build());
    }
    // end::create[]
    // tag::delete[]
    @DELETE
    @Path("{id}")
    public CompletionStage<Response> delete(@PathParam("id") Long id) {
        AsyncSession session = driver.asyncSession();
        return session
                .writeTransactionAsync(tx -> tx
                        .runAsync("MATCH (b:Book) WHERE id(b) = $id DELETE b",
                                Values.parameters("id", id))
                        .thenCompose(ResultCursor::consumeAsync))
                .thenCompose(resp -> session.closeAsync())
                .thenApply(signal -> Response.noContent().build());
    }
    // end::delete[]
}
