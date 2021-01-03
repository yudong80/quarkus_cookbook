package org.acme.quickstart;

import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;

@Path("/movie")
public class MovieResource {

    // tag::method[]
    @ResourcePath("movies/detail.html") // <1>
    Template movies;
    // end::method[]

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance movie() { 
        final Movie movie = new Movie();
        movie.name = "Star Trek: First Contact";
        movie.genre = "Sci-Fi";
        movie.ratings = 4.234f;
        movie.director = "Jonathan Frakes";
        movie.characters = Arrays.asList(
            "Jean-Luc Picard", "William Riker", 
            "Data", "Deanna Troi",
            "Beverly Crusher", "Worff");
        movie.year = 1996;

        return movies.data("movie", movie);
    }
    
    // tag::template[]
    @TemplateExtension
    static double roundStars(Movie movie, int decimals) { // <1> <2>
        double scale = Math.pow(10, decimals); 
        return Math.round(movie.ratings * scale) / scale;
    }
    // end::template[]

}
