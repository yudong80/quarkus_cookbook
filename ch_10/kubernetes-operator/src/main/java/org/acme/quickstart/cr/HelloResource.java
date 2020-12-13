package org.acme.quickstart.cr;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import io.fabric8.kubernetes.client.CustomResource;

@JsonDeserialize // <1>
public class HelloResource extends CustomResource { // <2>

    private HelloResourceSpec spec; // <3>
    private HelloResourceStatus status; // <4>

    public HelloResourceStatus getStatus() {
        return status;
    }

    public void setStatus(HelloResourceStatus status) {
        this.status = status;
    }

    public HelloResourceSpec getSpec() {
        return spec;
    }

    public void setSpec(HelloResourceSpec spec) {
        this.spec = spec;
    }

    @Override
    public String toString() {
        return "name=" + getMetadata().getName() 
                + ", version=" + getMetadata().getResourceVersion() 
                + ", spec=" + spec;
    }
}
