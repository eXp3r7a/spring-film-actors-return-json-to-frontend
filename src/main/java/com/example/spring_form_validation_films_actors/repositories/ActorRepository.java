package com.example.spring_form_validation_films_actors.repositories;

import com.example.spring_form_validation_films_actors.entities.Actor;
import com.example.spring_form_validation_films_actors.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
