package org.acme.quickstart;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class GreetingMain implements QuarkusApplication { // <1>

    @Override
    public int run(String... args) throws Exception { // <2>
        System.out.println("Hello World");
        Quarkus.waitForExit(); // <3>
        return 0;
    }

}