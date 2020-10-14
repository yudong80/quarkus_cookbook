package org.acme.transaction;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeTransactIT extends TransactTest {

    // Execute the same tests but in native mode.
}