package com.example.spring_form_validation_films_actors.repositories;

import com.example.spring_form_validation_films_actors.entities.Film;
import org.springframework.data.repository.CrudRepository;

public interface FilmRepository extends CrudRepository<Film, Long> {
}
