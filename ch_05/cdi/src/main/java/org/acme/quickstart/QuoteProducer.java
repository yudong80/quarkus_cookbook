package org.acme.quickstart;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class QuoteProducer {
    // tag::quote[]
    @Produces
    @Quote                                                          // <1>
    Message getQuote(InjectionPoint msg) {
        Quote q = msg.getAnnotated().getAnnotation(Quote.class);    // <2>
        return new Message(q.msg(), q.source());                    // <3>
    }
    // end::quote[]

}
