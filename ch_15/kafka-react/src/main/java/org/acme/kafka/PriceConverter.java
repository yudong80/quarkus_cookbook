package org.acme.kafka;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

@ApplicationScoped
public class PriceConverter {
    private static final double CONVERSION_RATE = 0.929127;

    @Incoming("prices")
    @Outgoing("in-memory-prices")
    @Broadcast
    public BigDecimal process(int priceInUSD) {
        return BigDecimal.valueOf(priceInUSD).multiply(BigDecimal.valueOf(CONVERSION_RATE));
    }
}
