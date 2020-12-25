package org.acme.quickstart;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@QuarkusTest
public class GreetingResourceTest {

    private static String validToken;

    @BeforeAll
    public static void loadToken() throws IOException {
        final InputStream tokenStream = GreetingResourceTest.class.getClassLoader().getResourceAsStream("token.jwt");
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(tokenStream))) {
            validToken = buffer.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    // tag::test[]
    @Test
    public void testHelloEndpoint() {
        given().header("Authorization", "Bearer " + validToken) // <1>
                .when().get("/hello").then().statusCode(200).body(is("hello jdoe"));
    }
    // end::test[]

}