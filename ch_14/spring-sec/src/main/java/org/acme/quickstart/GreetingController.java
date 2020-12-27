package org.acme.quickstart;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class GreetingController {
    @GetMapping
    @Secured("admin") // <1>
    public String helloAdmin() {
        return "hello from admin";
    }

    @PreAuthorize("hasAnyRole('user')") // <2>
    @GetMapping
    @RequestMapping("/any")
    public String helloUsers() {
        return "hello from users";
    }
}
