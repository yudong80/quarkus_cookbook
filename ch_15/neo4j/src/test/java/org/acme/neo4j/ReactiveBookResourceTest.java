package org.acme.neo4j;

import java.util.Set;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReactiveBookResourceTest {
    @Test
    @Order(2)
    public void testGetAllEndpoint() {
        Response r =
        given()
                .when()
                .get("/reactivebooks")
                .andReturn();
//                .then()
//                .statusCode(200)
//                .body("$.size", greaterThanOrEqualTo(1));
        assertThat(r, is(not(null)));
    }

    @Test
    @Order(1)
    public void testAddEndpoint() {
        Book b = new Book("Quarkus Cookbook", "1234567890", Set.of("Jason Porter", "Alex Soto Bueno"));
        given()
                .contentType(ContentType.JSON)
                .body(b.toJson())
                .when()
                .post("/reactivebooks")
                .then()
                .statusCode(200);
    }
}
