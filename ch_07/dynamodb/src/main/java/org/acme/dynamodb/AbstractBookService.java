package org.acme.dynamodb;

import java.util.HashMap;
import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public abstract class AbstractBookService {

    public final static String BOOK_TITLE = "title";
    public final static String BOOK_ISBN = "isbn";
    public final static String BOOK_AUTHOR = "author";

    public String getTableName() {
        return "QuarkusBook";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName()).build();
    }

    protected PutItemRequest putRequest(Book book) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(BOOK_ISBN, AttributeValue.builder()
                            .s(book.getIsbn()).build());
        item.put(BOOK_AUTHOR, AttributeValue.builder()
                              .s(book.getAuthor()).build());
        item.put(BOOK_TITLE, AttributeValue.builder()
                             .s(book.getTitle()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String isbn) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(BOOK_ISBN, AttributeValue.builder().s(isbn).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .build();
    }
}
