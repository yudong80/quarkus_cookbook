package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Readiness;

// tag::ready[]
@ApplicationScoped // <1>
public class CustomHealthCheck {

    @Produces // <2>
    @Readiness // <3>
    public HealthCheck ready() {
        if (isReady()) {
            return io.smallrye.health.HealthStatus.up("Custom readiness"); // <4>
        } else {
            return io.smallrye.health.HealthStatus.down("Custom readiness");
        }
    }
    // end::ready[]
    private boolean isReady() {
        return true;
    }
    
// tag::ready[]
}
// end::ready[]