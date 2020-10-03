// tag::base[]
package org.acme.quickstart;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "greeting") // <1>
public class GreetingConfiguration {

    public String message; // <2>
    public String suffix = "!"; // <3>
// end::base[]
    // tag::validation[]
    @Min(1) // <1>
    @Max(3) // <2>
    public Integer repeat;
    // end::validation[]

    // tag::innerconf[]
    public OutputConfiguration output; // <1>

    public static class OutputConfiguration {
        public List<String> recipients;
    }
    // end::innerconf[]

// tag::base[]
}
// end::base[]
