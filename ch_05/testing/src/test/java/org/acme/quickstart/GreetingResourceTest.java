package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest // <1>
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given() // <2>
          .when()
          .get("/hello") // <3>
          .then() // <4>
             .statusCode(200)
             .body(is("hello"));
    }
}
