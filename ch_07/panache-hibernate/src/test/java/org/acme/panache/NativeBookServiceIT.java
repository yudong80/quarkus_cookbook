package org.acme.panache;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeBookServiceIT extends BookServiceTest {

    // Execute the same tests but in native mode.
}
