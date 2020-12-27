package org.acme.quickstart;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/developer")
public class DeveloperResource {

    private final DeveloperRepository developerRepository;

    public DeveloperResource(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDeveloper(Developer developer) {
        developer = developerRepository.save(developer);
        return Response
                .created(
                        UriBuilder.fromResource(DeveloperResource.class).path(Long.toString(developer.getId())).build())
                .entity(developer).build();
    }
}