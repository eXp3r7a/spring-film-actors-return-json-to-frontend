package com.example.spring_form_validation_films_actors.mappers;

import com.example.spring_form_validation_films_actors.dto.FilmDTO;
import com.example.spring_form_validation_films_actors.entities.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {
    public Film toEntity(FilmDTO filmDTO){
        Film film = new Film();

        film.setTitle(filmDTO.getTitle());
        film.setPublishedYear(filmDTO.getPublishedYear());
        film.setGenre(filmDTO.getGenre());
        film.setActors(filmDTO.getActors());

        return film;
    }
}
