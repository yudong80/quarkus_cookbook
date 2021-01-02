// tag::base[]
package org.acme.pg;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
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

    public static Multi<Book> findAll(PgPool client) {
        return client.query("SELECT id, title, isbn " +
                            "FROM books ORDER BY title ASC")
                .onItem().produceMulti(Multi.createFrom()::iterable)
                .map(Book::from);
    }
// end::base[] 
    // tag::delete[]
    public static Uni<Boolean> delete(PgPool client, Long id) {
        return client.preparedQuery("DELETE FROM books " +
                                    "WHERE id = $1", Tuple.of(id))
                .map(rowSet -> rowSet.rowCount() == 1); // <1>
    }
    // end::delete[]
// tag::base[]
}
// end::base[]
