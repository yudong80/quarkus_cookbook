package org.acme.quickstart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // <1>
@RequestMapping("/greeting") // <2>
public class SpringController {

  @GetMapping // <3>
  public ResponseEntity<String> getMessage() { // <4>
    return ResponseEntity.ok("Hello");
  }

  @GetMapping("/{name}")
  public String hello(@PathVariable(name = "name") String name) { // <5>
    return "hello " + name;
  } 
}
