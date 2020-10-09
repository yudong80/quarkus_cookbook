package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class QuoteProducerTest {
    // tag::quote[]
    @Quote(msg = "Good-bye and hello, as always.", source = "Roger Zelazny")
    Message myQuote;
    // end::quote[]

    @Test
    void assertProductionCorrect() {
        assertThat(myQuote.quote).isEqualTo("Good-bye and hello, as always.");
        assertThat(myQuote.source).isEqualTo("Roger Zelazny");
    }

}
