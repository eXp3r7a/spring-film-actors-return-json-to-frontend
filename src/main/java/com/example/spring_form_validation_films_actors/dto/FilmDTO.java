package com.example.spring_form_validation_films_actors.dto;

import com.example.spring_form_validation_films_actors.entities.Actor;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmDTO {
    @NotNull
    private String title;

    @NotNull
    private String titleChecker;

    @NotNull
    private String genre;

    @NotNull(message = "More than 1900")
    @Min(1900)
    private int publishedYear;

    private List<Actor> allActorsList;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "films_actors",
            joinColumns = @JoinColumn(name = "film_film_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_actor_id")
    )
    @Size(min = 1, max = 4, message = "You must select between 1 and 4 actors.")
    private List<Actor> actors = new ArrayList<>();

    public List<Actor> getAllActorsList() {
        return allActorsList;
    }

    public void setAllActorsList(List<Actor> allActorsList) {
        this.allActorsList = allActorsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleChecker() {
        return titleChecker;
    }

    public void setTitleChecker(String titleChecker) {
        this.titleChecker = titleChecker;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
