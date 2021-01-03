package org.acme.quickstart;

import java.util.ArrayList;
import java.util.List;

import io.quarkus.qute.TemplateData;

@TemplateData // <1>
public class Movie {

    public String name;
    public int year;
    public String genre;
    public String director;
    public List<String> characters = new ArrayList<>();
    public float ratings;

    public int getStars() { // <2>
        return Math.round(ratings);
    }
}