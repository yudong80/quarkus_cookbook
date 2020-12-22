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
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    // tag::test[]
    @Test
    public void testSecuredHelloEndpoint() {
        given()
                .auth() // <1>
                .basic("alex", "soto") // <2>
                .when()
                .get("/hello")
                .then()
                .statusCode(200)
                .body(is("hello"));
    }
    // end::test[]
}