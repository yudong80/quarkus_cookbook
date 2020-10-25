package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello/retry")
          .then()
             .statusCode(200)
             .body(is("good bye"));
    }

    @Test
    public void testHelloEndpointTimeout() {
        given()
          .when().get("/hello/timeout")
          .then()
             .statusCode(200)
             .body(is("Timeout"));
    }
}