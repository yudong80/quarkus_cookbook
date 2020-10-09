// tag::base[]
package org.acme.quickstart;

import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

@ApplicationScoped
public class LocaleProducer {
    @Produces
    public Locale getDefaultLocale() {
        return Locale.getDefault();
    }
// end::base[]
    // tag::named[]
    @Produces
    @Named("en_US")
    public Locale getEnUSLocale() {
        return Locale.US;
    }

    @Produces
    @Named("es_ES")
    public Locale getEsESLocale() {
        return new Locale("es", "ES");
    }
    // end::named[]
    // tag::qualifer[]
    @Produces
    @SpainLocale
    public Locale getSpainLocale() {
        return new Locale("es", "ES");
    }
    // end::qualifer[]
// tag::base[]
}
// end::base[]
