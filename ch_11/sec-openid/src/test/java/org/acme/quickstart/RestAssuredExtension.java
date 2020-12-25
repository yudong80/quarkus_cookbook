package org.acme.quickstart;

import java.net.URI;
import java.net.URISyntaxException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {

  public static ResponseOptions<Response> getAccessToken(String url,
                                                         String clientId, 
                                                         String clientIdPwd,
                                                         String username, 
                                                         String password) {
    final RequestSpecification request = prepareRequest(url);
    try {
      return request
        .auth()
        .preemptive()
        .basic(clientId, clientIdPwd)
        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
        .urlEncodingEnabled(true)
        .formParam("username", username)
        .and()
        .formParam("password", password)
        .and()
        .formParam("grant_type", "password")
        .post(new URI(url));
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    } 
  }

  private static RequestSpecification prepareRequest(String url) {
    final RequestSpecBuilder builder = new RequestSpecBuilder();
    final RequestSpecification requestSpec = builder.build();
    return RestAssured.given().spec(requestSpec);
  } 
}
