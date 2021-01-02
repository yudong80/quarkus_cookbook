package org.acme.kafka;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.reactivestreams.Publisher;

public class CharacterGenerator {
    @Outgoing("letter-out")
    public Publisher<String> generate() {
        return Multi.createFrom()
                .ticks().every(Duration.ofSeconds(1))
                .map(tick -> {
                    final int i = ThreadLocalRandom.current().nextInt(95);
                    return String.valueOf((char) (i + 32));
                });
    }
}
