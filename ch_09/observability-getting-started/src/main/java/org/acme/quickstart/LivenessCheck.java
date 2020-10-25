package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

// tag::liveness[]
@ApplicationScoped // <1>
@Liveness // <2>
public class LivenessCheck implements HealthCheck { // <3>

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder checkResponseBuilder = HealthCheckResponse
        .named("custom liveness"); // <4>
        
        if(isUpAndRunning()) {
            return checkResponseBuilder.up().build(); // <5>
        } else {
            return checkResponseBuilder.down()
                .withData("reason", "Failed connection")
                .build(); // <6>
        }

    }
    // end::liveness[]
    private boolean isUpAndRunning() {
        return true;
    }
// tag::liveness[]
}
// end::liveness[]