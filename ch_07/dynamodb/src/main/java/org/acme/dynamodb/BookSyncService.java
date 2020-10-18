package org.acme.dynamodb;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class BookSyncService extends AbstractBookService {
    @Inject
    DynamoDbClient dynamoDbClient;

    public List<Book> findAll() {
        return dynamoDbClient.scanPaginator(scanRequest()).items().stream()
                .map(Book::from)
                .collect(Collectors.toList());
    }

    public List<Book> add(Book b) {
        dynamoDbClient.putItem(putRequest(b));
        return findAll();
    }

    public Book get(String isbn) {
        return Book.from(dynamoDbClient.getItem(getRequest(isbn)).item());
    }
}
