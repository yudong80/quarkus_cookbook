package org.acme.quickstart;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.fabric8.kubernetes.client.KubernetesClient;

@Path("/pod")
public class PodResource {

    @Inject // <1>
    KubernetesClient kubernetesClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{namespace}")
    public List<String> getPods(@PathParam("namespace") String namespace) {
        return kubernetesClient.pods() // <2>
                                .inNamespace(namespace) // <3>
                                .list().getItems()
                                .stream()
                                .map(p -> p.getMetadata().getGenerateName()) // <4>
                                .collect(Collectors.toList());
    }
}