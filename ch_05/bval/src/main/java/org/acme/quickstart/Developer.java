package org.acme.quickstart;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Developer {

    // tag::validation[]
    @Size(min = 4) // <1>
    private String name;

    @NotBlank // <2>
    private String favoriteLanguage;
    // end::validation[]
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
