package org.acme.mongodb;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.model.Filters;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import org.bson.Document;

@ApplicationScoped
public class ReactiveBookService {
    @Inject
    ReactiveMongoClient mongoClient;

    public Uni<List<Book>> list() {
        return getCollection().find()
                .map(Book::from).collectItems().asList();
    }

    public Uni<Void> add(Book b) {
        Document doc = new Document()
                .append("isbn", b.isbn)
                .append("title", b.title)
                .append("authors", b.authors);

        return getCollection().insertOne(doc);
    }

    public Uni<Book> findSingle(String isbn) {
        return Objects.requireNonNull(getCollection()
                .find(Filters.eq("isbn", isbn))
                .map(Book::from))
                .toUni();
    }

    private ReactiveMongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("book")
                .getCollection("book");
    }
}
