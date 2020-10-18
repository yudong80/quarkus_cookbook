package org.acme.mongodb.panache;

import java.time.LocalDate;
import java.util.List;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;

@MongoEntity(collection = "book", database = "book")    // <1>
public class Book extends PanacheMongoEntity {          // <2>
    public String title;
    public String isbn;
    public List<String> authors;

    @BsonProperty("pubDate")                            // <3>
    public LocalDate publishDate;

    public static Book findByIsbn(String isbn) {
        return find("isbn", isbn).firstResult(); // <4>
    }

    public static List<Book> findPublishedOn(LocalDate date) {
        return list("pubDate", date);
    }

}
