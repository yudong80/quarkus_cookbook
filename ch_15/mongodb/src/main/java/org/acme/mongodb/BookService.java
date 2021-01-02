package org.acme.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;

@ApplicationScoped
public class BookService {

    @Inject
    MongoClient mongoClient;

    public List<Book> list() {
        List<Book> list = new ArrayList<>();

        try (MongoCursor<Document> cursor = getCollection()
                                            .find()
                                            .iterator()) {
            cursor.forEachRemaining(doc -> list.add(Book.from(doc)));
        }

        return list;
    }

    public Book findSingle(String isbn) {
        Document document = Objects.requireNonNull(getCollection()
                .find(Filters.eq("isbn", isbn))
                .limit(1).first());
        return Book.from(document);
    }

    public void add(Book b) {
        Document doc = new Document()
                .append("isbn", b.isbn)
                .append("title", b.title)
                .append("authors", b.authors);
        getCollection().insertOne(doc);
    }

    private MongoCollection<Document> getCollection() {
        return mongoClient.getDatabase("book").getCollection("book");
    }
}
