package org.acme.quickstart;

import javax.inject.Inject;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
class RecommendationServiceTest {
    @Inject RecommendationService rs;

    @Test
    public void assertProductCreation() {
        assertThat(rs.getProducts()).isNotNull();
    }
}
