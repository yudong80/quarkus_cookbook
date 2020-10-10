package org.acme.quickstart;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest // <1>
public class GreetingServiceTest {

  @Inject // <2>
  GreetingService greetingService;

  @Test // <3>
  public void testGreetingServiceForYoungers() {

    Assertions.assertThatExceptionOfType(ConstraintViolationException.class) // <4>
      .isThrownBy(() -> greetingService.greetingMessage(15)); 
  }

  @Test
  public void testGreetingServiceForTeenagers() {
    String message = greetingService.greetingMessage(18);
    Assertions.assertThat(message).isEqualTo("Hey boys and girls");
  }

  @Test
  public void testGreetingServiceForAdult() {
    String message = greetingService.greetingMessage(21);
    Assertions.assertThat(message).isEqualTo("Hey ladies and gentlemen");
  }

}
