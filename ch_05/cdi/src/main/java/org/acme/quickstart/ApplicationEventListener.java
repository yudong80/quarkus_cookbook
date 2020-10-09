package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped                                  // <1>
public class ApplicationEventListener {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ApplicationEventListener.class);

    void onStart(@Observes StartupEvent event) {    // <2>
        LOGGER.info("Application starting...");
    }

    void onStop(@Observes ShutdownEvent event) {    // <3>
        LOGGER.info("Application shutting down...");
    }

}
