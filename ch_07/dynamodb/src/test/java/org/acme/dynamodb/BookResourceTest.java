package org.acme.dynamodb;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void testGetAllEndpoint() {
        given()
          .when().get("/books")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

//    @Test
//    public void testPostEndpoint() {
//        Book b = new Book();
//        b.setTitle("Quarkus Cookbook");
//        b.setAuthor("Jason Porter, Alex Soto");
//        b.setIsbn("1234567890");
//
//        given()
//                .when().body(b).post("/books")
//                .then()
//                    .statusCode(200)
//                    .body(hasItem(b));
//    }
}
