package org.acme.neo4j;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import org.neo4j.driver.Values;
import org.neo4j.driver.types.Node;

public class Book {
  public Long id;
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

  public Book(Long id, String title, String isbn, Set<String> authors) {
    this.id = id;
    this.title = title;
    this.isbn = isbn;
    this.authors = authors;
  }

  public static Book from(Node node) {
    return new Book(node.id(),
        node.get("title").asString(),
        node.get("isbn").asString(),
        new HashSet<>(
          node.get("authors")
          .asList(Values.ofString())
          )
        );
  }

  public String toJson() {
    final StringJoiner authorString = 
      new StringJoiner("\",\"", "[\"", "\"]");

    authors.forEach(authorString::add);

    return "{" +
      "\"title\":\"" + this.title + "\"," +
      "\"isbn\":\"" + this.isbn + "\"," +
      "\"authors\":" + authorString.toString() +
      "}";
  }
}
