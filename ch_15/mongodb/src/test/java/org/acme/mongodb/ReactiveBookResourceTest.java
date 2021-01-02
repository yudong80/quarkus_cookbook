package org.acme.mongodb;

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
public class ReactiveBookResourceTest {
    @Test
    @Order(2)
    public void testGetAllEndpoint() {
        given()
        .when()
            .get("/reactive_books")
        .then()
            .statusCode(200)
            .body("$.size", greaterThanOrEqualTo(1));
    }

    @Test
    @Order(3)
    public void testGetSingleEndpoint() {
        given()
        .when()
            .get("/reactive_books/1234567890")
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
            .body(b)
        .when()
            .post("/reactive_books").andReturn()
        .then()
            .statusCode(201);
    }

}
