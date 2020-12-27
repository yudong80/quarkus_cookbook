package org.acme.quickstart;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // <1>
public class AppConfiguration {

    @Bean(name = "suffix") // <2>
    public Function<String, String> exclamation() {
        return new Function<String, String>() { // <3>
            @Override
            public String apply(String t) {
                return t + "!!";
            }
        };
    }
}