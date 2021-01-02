package org.acme.kafka;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

@Path("/prices")
public class PriceResource {
    @Inject
    @Channel("in-memory-prices")
    Publisher<BigDecimal> prices;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<BigDecimal> stream() {
        return Multi.createFrom().publisher(prices)
                .map(d -> d.setScale(2, RoundingMode.HALF_UP));
    }
}
