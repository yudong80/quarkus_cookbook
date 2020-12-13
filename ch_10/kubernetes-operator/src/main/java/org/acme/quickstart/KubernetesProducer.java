package org.acme.quickstart;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Singleton;

import org.acme.quickstart.cr.HelloResource;
import org.acme.quickstart.cr.HelloResourceDoneable;
import org.acme.quickstart.cr.HelloResourceList;

import io.fabric8.kubernetes.api.model.apiextensions.CustomResourceDefinition;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;

public class KubernetesProducer {

  @Produces
  @Singleton
  @Named("namespace")
  String findMyCurrentNamespace() throws IOException { // <1>
    return new String(Files.readAllBytes(
          Paths
            .get("/var/run/secrets/kubernetes.io/serviceaccount/namespace")));
  }

  @Produces
  @Singleton
  KubernetesClient makeDefaultClient(@Named("namespace") String namespace) {
    return new DefaultKubernetesClient().inNamespace(namespace); // <2>
  }

  @Produces
  @Singleton
  MixedOperation<HelloResource, 
                 HelloResourceList, 
                 HelloResourceDoneable, 
                 Resource<HelloResource, HelloResourceDoneable>>
  makeCustomHelloResourceClient(KubernetesClient defaultClient) { // <3>
    KubernetesDeserializer
        .registerCustomKind("acme.org/v1alpha1", 
                            "Hello", HelloResource.class); // <4>
    CustomResourceDefinition crd = defaultClient.customResourceDefinitions()
                                      .list()
                                      .getItems()
                                      .stream()
                                      .findFirst()
      .orElseThrow(RuntimeException::new); // <5>
    return defaultClient.customResources(crd, HelloResource.class, 
        HelloResourceList.class,
        HelloResourceDoneable.class); // <6>
  } 
}
