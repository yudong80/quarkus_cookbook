package org.acme.dynamodb;

import javax.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class BookSyncServiceTest {
    @Inject
    BookSyncService service;

    @Test
    void testAdd() {
        Book b = new Book();
        b.setTitle("Quarkus Cookbook");
        b.setAuthor("Jason Porter, Alex Soto");
        b.setIsbn("1234567890");

        assertThat(service.add(b)).contains(b, Index.atIndex(0));
    }
}
