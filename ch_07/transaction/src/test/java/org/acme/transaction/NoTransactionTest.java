package org.acme.transaction;

import javax.inject.Inject;
import javax.transaction.TransactionalException;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@QuarkusTest
public class NoTransactionTest {
    @Inject
    Transact tx;

    @Test
    void assertException() {
        assertThatExceptionOfType(TransactionalException.class).isThrownBy(() -> tx.hi());
    }
}
