package org.acme.kafka;

import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class BookDeserializer extends JsonbDeserializer<Book> {
    public BookDeserializer() {
        super(Book.class);
    }
}
