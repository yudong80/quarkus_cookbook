package org.acme.quickstart;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

// tag::custom[]
@RegisterClientHeaders(CustomClientHeadersFactory.class) // <1>
public interface ConfigureHeaderServices {
// end::custom[]

    // tag::static[]
    @Path("/somePath")
    @ClientHeaderParam(name="user-agent", value="curl/7.54.0") // <1>
    Response get();
    // end::static[]

    // tag::static_method[]
    @ClientHeaderParam(name="user-agent", value="{determineHeaderValue}") // <1>
    Response otherGet();

    default String determineHeaderValue(String headerName) { // <2>
        return "Hi-" + headerName;
    }
    // end::static_method[]

    // tag::multipart[]
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA) // <1>
    @Produces(MediaType.TEXT_PLAIN)
    String sendMultipartData(@MultipartForm // <2>
                                MultipartDeveloperModel data); // <3>
    // end::multipart[]
}