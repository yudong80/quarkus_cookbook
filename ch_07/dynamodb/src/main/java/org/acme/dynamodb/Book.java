package org.acme.dynamodb;

import java.util.Map;
import java.util.Objects;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RegisterForReflection      // <1>
public class Book {
    private String isbn;
    private String author;
    private String title;

    public Book() {         // <2>
    }

    public static Book from(Map<String, AttributeValue> item) {
        Book b = new Book();
        if (item != null && !item.isEmpty()) {
            b.setAuthor(item.get(AbstractBookService.BOOK_AUTHOR).s());
            b.setIsbn(item.get(AbstractBookService.BOOK_ISBN).s());
            b.setTitle(item.get(AbstractBookService.BOOK_TITLE).s());
        }
        return b;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, author, title);
    }
}
