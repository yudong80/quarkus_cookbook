package org.acme.reactive.mysql;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.mysqlclient.MySQLClient;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

public class Book {

    public Long id;
    public String title;
    public String isbn;

    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Book(Long id, String title, String isbn) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
    }

    public static Book from(Row row) {
        return new Book(row.getLong("id"),
                row.getString("title"),
                row.getString("isbn"));
    }

    public static Multi<Book> findAll(MySQLPool client) {
        return client.query("SELECT id, title, isbn " +
                            "FROM books ORDER BY title ASC")
                .onItem().produceMulti(Multi.createFrom()::iterable)
                .map(Book::from);
    }

    // tag::save[]
    public Uni<Long> save(MySQLPool client) {
        String query = "INSERT INTO books (title,isbn) VALUES (?,?)";
        return client.preparedQuery(query, Tuple.of(title, isbn))
                .map(rowSet -> rowSet
                        .property(MySQLClient.LAST_INSERTED_ID)); // <1>
    }
    // end::save[]
}
