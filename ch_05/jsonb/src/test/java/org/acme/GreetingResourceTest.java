package org.acme;

import static io.restassured.RestAssured.given;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class GreetingResourceTest {

    @Test
    @Order(1)
    public void testInsertDeveloper() {

        given()
          .contentType("application/json")
          .body("{\"name\":\"Alex\",\"age\":39, \"favoriteLanguage\":\"java\"}")
          .when()
            .post("/developer")
            .then()
              .assertThat()
              .statusCode(200);
    }

    @Test
    @Order(2)
    public void testGetDevelopers() {
        given()
          .when().get("/developer")
          .then()
             .statusCode(200)
             .body("name", CoreMatchers.hasItem("Alex"));
    }

}
