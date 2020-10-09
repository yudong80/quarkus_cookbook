package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class InterceptorTest {
    @Test
    @LogEvent
    void executeOrder() {
        assertThat(LogEventInterceptor.events).isNotEmpty();
    }
}
