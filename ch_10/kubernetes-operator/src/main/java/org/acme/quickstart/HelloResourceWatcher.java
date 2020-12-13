package org.acme.quickstart;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.acme.quickstart.cr.HelloResource;
import org.acme.quickstart.cr.HelloResourceDoneable;
import org.acme.quickstart.cr.HelloResourceList;

import io.fabric8.kubernetes.api.model.ContainerBuilder;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodSpecBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.quarkus.runtime.StartupEvent;

public class HelloResourceWatcher {

  @Inject
  KubernetesClient defaultClient; // <1>

  @Inject
  MixedOperation<HelloResource, 
    HelloResourceList, 
    HelloResourceDoneable, 
    Resource<HelloResource, 
    HelloResourceDoneable>> crClient; // <2>

  void onStartup(@Observes StartupEvent event) { // <3> 
    crClient.watch(new Watcher<HelloResource>() { // <4>
      @Override
      public void eventReceived(Action action, HelloResource resource) {
        System.out.println("Received " + action 
            + " event for resource " + resource);
        if (action == Action.ADDED) {
          final String app = resource.getMetadata().getName(); // <5>
          final String message = resource.getSpec().getMessage();

          final Map<String, String> labels = new HashMap<>(); // <6>
          labels.put("app", app);

          final ObjectMetaBuilder objectMetaBuilder = 
            new ObjectMetaBuilder().withName(app + "-pod")
            .withNamespace(resource.getMetadata()
                .getNamespace())
            .withLabels(labels);

          final ContainerBuilder containerBuilder = 
            new ContainerBuilder().withName("whalesay")
            .withImage("docker/whalesay")
            .withCommand("cowsay", message); // <7>

          final PodSpecBuilder podSpecBuilder = 
            new PodSpecBuilder()
            .withContainers(containerBuilder.build())
            .withRestartPolicy("Never");

          final PodBuilder podBuilder = 
            new PodBuilder()
            .withMetadata(objectMetaBuilder.build())
            .withSpec(podSpecBuilder.build());

          final Pod pod = podBuilder.build(); // <8>
          HasMetadata result = defaultClient
            .resource(pod)
            .createOrReplace(); // <9>

          if (result == null) {
            System.out.println("Pod " + pod 
                + " couldn't be created");
          } else {
            System.out.println("Pod " + pod + " created");
          }
        }
      }

      @Override
      public void onClose(KubernetesClientException e) { // <10>
        if (e != null) {
          e.printStackTrace();
          System.exit(-1);
        }
      }
    }); 
  } 
}
