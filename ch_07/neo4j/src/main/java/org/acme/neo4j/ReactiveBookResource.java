package org.acme.neo4j;

import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;
import org.neo4j.driver.reactive.RxResult;
import org.reactivestreams.Publisher;

@Path("/reactivebooks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReactiveBookResource {
    @Inject
    Driver driver;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS) // <3>
    public Publisher<Response> getAll() {
        return Multi.createFrom().resource( // <2>
                driver::rxSession,
                rxSession -> rxSession.readTransaction(tx -> { // <1>
                    RxResult result = tx.run("MATCH (b:Book) RETURN " +
                                             "b ORDER BY b.title");
                    return Multi.createFrom().publisher(result.records())
                            .map(Record::values)
                            .map(values -> values.stream().map(Value::asNode)
                                                          .map(Book::from)
                                                          .map(Book::toJson))
                            .map(bookStream ->
                                    Response.ok(bookStream
                                            .collect(Collectors.toList()))
                                    .build());
                }))
                .withFinalizer(rxSession -> { // <4>
                    return Uni.createFrom().publisher(rxSession.close());
                });
    }

    @POST
    public Publisher<Response> create(Book b) {
        return Multi.createFrom().resource(
                driver::rxSession,
                rxSession -> rxSession.writeTransaction(tx -> {
                    String query = "CREATE " +
                                   "(b:Book {title: $title, isbn: $isbn," +
                                   " authors: $authors}) " +
                                   "RETURN b";
                    RxResult result = tx.run(query,
                            Values.parameters("title", b.title,
                                    "isbn", b.isbn, "authors", b.authors));
                    return Multi.createFrom().publisher(result.records())
                            .map(record -> Response.ok(record
                                    .asMap()).build());
                })
        ).withFinalizer(rxSession -> {
            return Uni.createFrom().publisher(rxSession.close());
        });
    }
}
