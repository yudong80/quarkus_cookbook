// tag::base[]
package org.acme.quickstart;

import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped                  // <1>
public class GreetingService {
    public String getGreeting() {
        return "Hello";
    }
// end::base[]

// tag::named[]
    @Inject
    @Named("en_US")
    Locale en_US;

    @Inject
    @Named("es_ES")
    Locale es_ES;

    public String getGreeting(String locale) {
        if (locale.startsWith("en"))
            return "Hello from " + en_US.getDisplayCountry();

        if (locale.startsWith("es"))
            return "Hola desde " + es_ES.getDisplayCountry();

        return "Unknown locale";
    }
    // end::named[]
    // tag::qualifer[]
    @Inject
    @SpainLocale
    Locale spain;
    // end::qualifer[]
// tag::base[]
}
// end::base[]
