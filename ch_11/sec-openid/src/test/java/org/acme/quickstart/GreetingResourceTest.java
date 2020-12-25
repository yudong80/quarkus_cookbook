package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    private static String accessToken;

    @BeforeAll
    public static void getAccessToken() {
        final ResponseOptions<Response> response = RestAssuredExtension.GetAccessToken("http://localhost:8180/auth/realms/quarkus/protocol/openid-connect/token",
                "backend-service", "secret", "alice", "alice");
        accessToken = response.getBody().jsonPath().getString("access_token");
    }

    // tag::test[]
    @Test
    public void testHelloEndpoint() {
        System.out.println(accessToken);
        given()
          .auth().oauth2(accessToken)
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello alice"));
    }
    // end::test[]
}