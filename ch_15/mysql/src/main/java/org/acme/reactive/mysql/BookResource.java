package org.acme.reactive.mysql;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    MySQLPool client;
    // tag::setup[]
    // This is all done for ease of demonstration
    // We DO NOT recommend this to be done in production

    // Here we're setting up the schema and adding data to the database
    // This value can be overridden via application.properties or other means
    // of application configuration
    @Inject
    @ConfigProperty(name = "my.schema.create", defaultValue = "true")
    boolean schemaCreate;

    @PostConstruct
    void config() {
        if (schemaCreate)
            initDb();
    }

    // Schema creation and seed data
    private void initDb() {
        String booksSchema = "CREATE TABLE books (" +
                             "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                             "title TEXT NOT NULL, " +
                             "isbn VARCHAR(17) NOT NULL)";
        client.query("DROP TABLE IF EXISTS books")
                .flatMap(r -> client.query(booksSchema))
                .flatMap(r -> client.query("INSERT INTO books (title, isbn) VALUES ('Quarkus Cookbook: Kubernetes Optimized Java Solutions', '978-1-492-06265-3')"))
                .flatMap(r -> client.query("INSERT INTO books (title, isbn) VALUES ('Testing Java Microservices', '978-1-617-29289-7')"))
                .flatMap(r -> client.query("INSERT INTO books (title, isbn) VALUES ('Mistborn', '978-0-765-35038-1')"))
                .await().indefinitely();
    }
    // end::setup[]
    // tag::get[]
    @GET
    public Uni<Response> get() {
        return Book.findAll(client)
                .collectItems().asList()
                .map(Response::ok)
                .map(Response.ResponseBuilder::build);
    }
    // end::get[]

    @POST
    public Uni<Response> create(Book book) {
        return book.save(client)
                .map(id -> URI.create("/books/" + id))
                .map(uri -> Response.created(uri).build());
    }
}
