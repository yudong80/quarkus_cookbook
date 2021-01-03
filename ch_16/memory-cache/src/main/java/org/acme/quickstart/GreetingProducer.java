package org.acme.quickstart;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.cache.CacheResult;

@ApplicationScoped
public class GreetingProducer {

    private static Random random = new Random();

    // tag::cache[]
    @CacheResult(cacheName = "greeting-cache") // <1>
    public String getMessage() {
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(4) + 1);
            return "Hello World";
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

    }
    // end::cache[]
}