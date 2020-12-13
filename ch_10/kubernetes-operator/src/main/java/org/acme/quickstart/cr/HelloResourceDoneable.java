package org.acme.quickstart.cr;

import io.fabric8.kubernetes.api.builder.Function;
import io.fabric8.kubernetes.client.CustomResourceDoneable;

public class HelloResourceDoneable 
    extends CustomResourceDoneable<HelloResource> { // <1>
    public HelloResourceDoneable(HelloResource resource, Function<HelloResource, 
                                 HelloResource> function) {
        super(resource, function);
    } 
}
