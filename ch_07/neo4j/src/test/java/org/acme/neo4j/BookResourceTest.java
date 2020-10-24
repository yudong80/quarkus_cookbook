package org.acme.neo4j;

import java.util.Set;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {
    @Test
    @Order(2)
    public void testGetAllEndpoint() {
        given()
                .when()
                .get("/book")
                .then()
                .statusCode(200)
                .body("$.size", greaterThanOrEqualTo(1));
    }

    @Test
    @Order(3)
    public void testGetSingleEndpoint() {
        given()
                .when()
                .get("/book/0")
                .then()
                .statusCode(200)
                .assertThat().body("title", equalTo("Quarkus Cookbook"));
    }

    @Test
    @Order(1)
    public void testAddEndpoint() {
        Book b = new Book("Quarkus Cookbook", "1234567890", Set.of("Jason Porter", "Alex Soto Bueno"));
        given()
                .contentType(ContentType.JSON)
                .body(b.toJson())
                .when()
                .post("/book")
                .then()
                .statusCode(201);
    }

}
