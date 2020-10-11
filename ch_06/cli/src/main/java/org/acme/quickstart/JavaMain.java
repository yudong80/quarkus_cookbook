package org.acme.quickstart;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain // <1>
public class JavaMain {

    public static void main(String... args) {
        Quarkus.run(GreetingMain.class, args); // <2>
    }

}