package org.acme.quickstart;

import io.quarkus.test.Mock;

@Mock // <1>
public class MockedGreetingService 
    extends GreetingService { // <2>

        @Override
        public String greetingMessage(int age) {
            return "Hello World"; // <3>
        }
    
}