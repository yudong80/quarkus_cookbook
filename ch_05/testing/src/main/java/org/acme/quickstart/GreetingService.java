package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.Min;

@ApplicationScoped // <1>
public class GreetingService {

    public String greetingMessage(@Min(value = 16) int age) { // <2>
        if (age < 19) {
            return "Hey boys and girls";
        } else {
            return "Hey ladies and gentlemen";
        }
    }

}
