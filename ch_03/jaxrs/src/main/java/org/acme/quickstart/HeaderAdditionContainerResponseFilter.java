package org.acme.quickstart;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider // <1>
public class HeaderAdditionContainerResponseFilter 
                implements ContainerResponseFilter { // <2>

    @Override
    public void filter(ContainerRequestContext requestContext, 
                       ContainerResponseContext responseContext)
            throws IOException {
                responseContext.getHeaders()
                  .add("X-Header", "Header added by JAXRS Filter"); // <3>
    } 
}
