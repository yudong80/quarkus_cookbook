package org.acme.panache;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Book extends PanacheEntity {
    @Column(name = "isbn", nullable = false, unique = true)
    public String isbn;

    public String author;
    public String title;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<String> topics;

    // tag::find[]
    public static Book findByTitle(String title) {
        return find("title", title).firstResult();
    }

    public static List<Book> findByAuthor(String author) {
        return list("author", author);
    }

    public static List<Book> findByIsbn(String isbn) {
        return list("isbn", isbn);
    }
    // end::find[]

    public void addTopic(String topic) {
        if (this.topics == null) {
            this.topics = new ArrayList<>();
        }
        this.topics.add(topic);
    }
}
