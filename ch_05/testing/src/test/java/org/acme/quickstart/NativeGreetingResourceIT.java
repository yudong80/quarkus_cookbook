package org.acme.quickstart;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest // <1>
public class NativeGreetingResourceIT 
    extends GreetingResourceTest { // <2>

    // Execute the same tests but in native mode.
}