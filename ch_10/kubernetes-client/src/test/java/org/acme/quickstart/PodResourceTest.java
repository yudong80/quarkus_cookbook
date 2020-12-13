package org.acme.quickstart;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodListBuilder;
import io.fabric8.kubernetes.client.server.mock.KubernetesMockServer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.KubernetesMockServerTestResource;
import io.quarkus.test.kubernetes.client.MockServer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(KubernetesMockServerTestResource.class) // <1>
public class PodResourceTest {

    @MockServer // <2>
    KubernetesMockServer mockServer;

    @BeforeEach // <3>
    public void prepareKubernetesServerAPI() {
        final Pod pod1 = new PodBuilder()
                .withNewMetadata()
                .withName("pod1")
                .withNamespace("test")
                .withGenerateName("pod1-12345")
                .and()
                .build(); // <4>

        mockServer
                .expect()
                  .get()
                    .withPath("/api/v1/namespaces/test/pods")
                    .andReturn(200, new PodListBuilder()
                        .withNewMetadata()
                        .withResourceVersion("1")
                        .endMetadata()
                        .withItems(pod1).build()) // <5>
                .always();

    }

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/pod/test")
          .then()
             .statusCode(200)
             .body(is("[\"pod1-12345\"]"));
    }

}
