package com.example.spring_form_validation_films_actors.repositories;
import com.example.spring_form_validation_films_actors.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User,Long> {
    public User getUserByUsername(@Param("username") String username);
    public User getUserByEmail(@Param("email") String email);
}
