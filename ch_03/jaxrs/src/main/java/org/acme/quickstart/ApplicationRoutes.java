// tag::basic[]
package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.quarkus.vertx.http.runtime.filters.Filters;
import io.quarkus.vertx.web.Route;
import io.vertx.core.http.HttpMethod;   // <5>
import io.vertx.ext.web.Router;         // <5>
import io.vertx.ext.web.RoutingContext; // <5>

@ApplicationScoped // <1>
public class ApplicationRoutes {

    public void routes(@Observes Router router) { // <2>

        router
            .get("/ok") // <3>
            .handler(rc -> rc.response().end("OK from Route")); // <4>

    }
    // end::basic[]    
    // tag::declarative[]
    @Route(path = "/declarativeok", methods = HttpMethod.GET) // <1>
    public void greetings(RoutingContext routingContext) { // <2>
        String name = routingContext.request().getParam("name"); // <3>

        if (name == null) {
            name = "world";
        }

        routingContext.response().end("OK " + name + " you are right"); // <4>
    }
    // end::declarative[]
    // tag::filter[]
    public void filters(@Observes Filters filters) { // <1>
        filters
            .register(
                rc -> {
                    rc.response() // <2>
                        .putHeader("V-Header", "Header added by VertX Filter"); // <3>
                    rc.next(); // <4>
                },
                10); // <5>
    }
    // end::filter[]
// tag::basic[]
}
// end::basic[]
