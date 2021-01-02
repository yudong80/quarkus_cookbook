package org.acme.kafka;

public class Book {
    public String title;
    public String author;
    public Long isbn;

    public Book() {
    }

    public Book(String title, String author, Long isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
