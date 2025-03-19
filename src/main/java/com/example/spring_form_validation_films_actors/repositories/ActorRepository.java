package com.example.spring_form_validation_films_actors.repositories;

import com.example.spring_form_validation_films_actors.entities.Actor;
import org.springframework.data.repository.CrudRepository;

public interface ActorRepository extends CrudRepository<Actor, Long> {
}
