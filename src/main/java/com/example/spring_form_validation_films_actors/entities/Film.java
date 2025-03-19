package com.example.spring_form_validation_films_actors.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long film_id;

    @NotNull
    private String title;

    @NotNull
    private String genre;

    @NotNull(message = "More than 1900")
    @Min(1900)
    private int publishedYear;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "films_actors",
            joinColumns = @JoinColumn(name = "film_film_id"),
            inverseJoinColumns = @JoinColumn(name = "actors_actor_id")
    )
    @Size(min = 1, max = 4, message = "You must select between 1 and 4 actors.")
    private List<Actor> actors = new ArrayList<>();

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
