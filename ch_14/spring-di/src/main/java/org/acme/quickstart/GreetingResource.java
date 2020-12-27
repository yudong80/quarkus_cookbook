package org.acme.quickstart;

import java.util.function.Function;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

@Path("/greeting")
public class GreetingResource {
    // tag::config[]
    @Value("${greetings.message:Hi}") // <1>
    String message;
    // end::config[]

    // tag::configbean[]
    @Autowired // <1>
    @Qualifier("suffix") // <2>
    Function<String, String> suffixComponent;
    // end::configbean[]

    // tag::service[]
    private PrefixService prefixService;

    public GreetingResource(PrefixService prefixService) { // <1>
        this.prefixService = prefixService;
    }
    // end::service[]

    // tag::body[]
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String prefixed = prefixService.appendPrefix(message);
        return this.suffixComponent.apply(prefixed);
    }
    // end::body[]
}