package org.acme;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static io.restassured.RestAssured.given;

import org.hamcrest.CoreMatchers;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class DeveloperResourceTest {

    @Test
    @Order(1)
    public void testInsertDeveloper() {

        given()
          .contentType("application/json")
          .body("{\"name\":\"Alexandra\",\"age\":39, \"favoriteLanguage\":\"java\"}")
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
             .body("name", CoreMatchers.hasItem("Alexandra"));
    }

    @Test
    @Order(3)
    public void testInsertDeveloper2() {

        given()
          .contentType("application/json")
          .body("{\"name\":\"Ada\",\"age\":39, \"favoriteLanguage\":\"java\"}")
          .when()
            .post("/developer")
            .then()
              .assertThat()
              .statusCode(400);
    }

}
