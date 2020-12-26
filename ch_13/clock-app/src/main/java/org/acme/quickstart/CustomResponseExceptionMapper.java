package org.acme.quickstart;

import java.io.IOException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class CustomResponseExceptionMapper
                implements ResponseExceptionMapper<IOException> { // <1>

    @Override
    public IOException toThrowable(Response response) { // <2>
        return new IOException();
    }

    @Override
    public boolean handles(int status,
                            MultivaluedMap<String, Object> headers) { // <3>
        return status >= 400 && status < 500;
    }

}
