package org.acme.kafka;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

@ApplicationScoped
public class PriceGenerator {
    @Outgoing("generated-price")
    public Publisher<Integer> generate() {
        return Multi.createFrom()
                .ticks().every(Duration.ofSeconds(1))
                .map(tick -> ThreadLocalRandom.current().nextInt(100));
    }
}
