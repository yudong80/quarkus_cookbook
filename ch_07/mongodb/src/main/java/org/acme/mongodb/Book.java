package org.acme.mongodb;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.bson.Document;

public class Book {
    public String id;
    public String title;
    public String isbn;
    public Set<String> authors;

    // Needed for JSON-B
    public Book() {}

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Book(String title, String isbn, Set<String> authors) {
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
    }

    public Book(String id, String title, String isbn, Set<String> authors) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.authors = authors;
    }

    public static Book from(Document doc) {
        return new Book(doc.getString("id"),
                        doc.getString("title"),
                        doc.getString("isbn"),
                        new HashSet<>(doc.getList("authors", String.class)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(authors, book.authors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, authors);
    }
}
