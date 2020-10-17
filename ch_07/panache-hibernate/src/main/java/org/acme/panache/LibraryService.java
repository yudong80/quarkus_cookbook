package org.acme.panache;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/library")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class LibraryService {

    @Inject
    LibraryRepository libraryRepository;

    @GET
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll().list();
    }

    @GET
    @Path("/{name}")
    public Library getLibraryByName(@PathParam("name") String name) {
        return libraryRepository.findByName(name);
    }

    // tag::persist[]
    @POST
    public Response newLibrary(Library library) {
        library.persist();
        return Response.created(URI.create("/library/" + library.encodedName()))
                .entity(library).build();
    }
    // end::persist[]
}
