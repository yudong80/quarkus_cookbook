package org.acme.quickstart;

import java.util.Collections;
import java.util.Map;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class HelloWorldQuarkusTestResourceLifecycleManager 
    implements QuarkusTestResourceLifecycleManager { // <1>

    @Override
    public Map<String, String> start() { // <2>
        System.out.println("Start Test Suite execution");
        return Collections.emptyMap(); // <3>
    }

    @Override
    public void stop() { // <4>
        System.out.println("Stop Test Suite execution");
    }

    @Override
    public void inject(Object testInstance) { // <5>
        System.out.println("Executing " + testInstance.getClass().getName());
    }

    @Override
    public int order() { // <6>
        return 0;
    }
        
}