package org.acme.quickstart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class GreetingResource {

    // tag::method[]
    @Autowired // <1>
    GreetingConfiguration greetingConfiguration;

    @GetMapping
    public String hello() {
        if (greetingConfiguration.getConfiguration().isUppercase()) { // <2>
            return greetingConfiguration.getMessage().toUpperCase();
        }
        return greetingConfiguration.getMessage();
    }
    // end::method[]
}