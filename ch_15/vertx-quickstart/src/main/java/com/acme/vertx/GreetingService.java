// tag::base[]
package com.acme.vertx;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class GreetingService {
    @ConsumeEvent   // <1>
    public String consumeNormal(String name) { // <2>
        return name.toUpperCase();
    } 
// end::base[]
    // tag::named[]
    @ConsumeEvent(value = "greeting")
    public String consumeNamed(String name) {
        return name.toUpperCase();
    }
    // end::named[]
// tag::base[]
}
// end::base[]

