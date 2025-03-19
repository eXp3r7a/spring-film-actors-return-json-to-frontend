package com.example.spring_form_validation_films_actors.mappers;

import com.example.spring_form_validation_films_actors.dto.ActorDTO;
import com.example.spring_form_validation_films_actors.entities.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {
    public Actor toEntity(ActorDTO actorDTO){
        Actor actor = new Actor();
        actor.setName(actorDTO.getName());
        actor.setLastname(actorDTO.getLastname());
        actor.setCountry(actorDTO.getCountry());
        actor.setAge(actorDTO.getAge());
        actor.setGender(actorDTO.getGender());

        actor.setEmail(actorDTO.getName() + "@mail.com");
        actor.setPhoneNumber("0880011223");

        return actor;
    }
}
