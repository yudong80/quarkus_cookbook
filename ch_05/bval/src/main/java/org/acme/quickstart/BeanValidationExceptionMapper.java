package org.acme.quickstart;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider // <1>
public class BeanValidationExceptionMapper 
  implements ExceptionMapper<ConstraintViolationException> { // <2>

    @Override
    public Response toResponse(ConstraintViolationException exception) {
      return Response.status(Response.Status.BAD_REQUEST)
        .entity(createErrorMessage(exception))
        .type(MediaType.APPLICATION_JSON)
        .build();
    }

    private JsonArray createErrorMessage(ConstraintViolationException exc) {
      JsonArrayBuilder errors = Json.createArrayBuilder(); // <3>
      for (ConstraintViolation<?> violation : exc.getConstraintViolations()) { // <4>
        errors.add(
            Json.createObjectBuilder() // <5>
            .add("path", violation.getPropertyPath().toString())
            .add("message", violation.getMessage())
            );
      }
      return errors.build();
    } 
}
