package com.example.spring_form_validation_films_actors.services;

import com.example.spring_form_validation_films_actors.dto.ActorDTO;
import com.example.spring_form_validation_films_actors.dto.ResponseMessage;
import com.example.spring_form_validation_films_actors.entities.Actor;
import com.example.spring_form_validation_films_actors.entities.Country;
import com.example.spring_form_validation_films_actors.mappers.ActorMapper;
import com.example.spring_form_validation_films_actors.repositories.ActorRepository;
import com.example.spring_form_validation_films_actors.repositories.CountryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    private final CountryRepository countryRepository;

    private final ActorMapper actorMapper;

    public ActorService(ActorRepository actorRepository, CountryRepository countryRepository, ActorMapper actorMapper) {
        this.actorMapper = actorMapper;
        this.countryRepository = countryRepository;
        this.actorRepository = actorRepository;
    }

    public ActorDTO addActorForm(){
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setCountries(countryRepository.findAll());
        return actorDTO;
    }

    public ResponseMessage submitActorToDB(@Valid ActorDTO actorDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            addActorForm();
        }

        Optional<Country> countryOptional = countryRepository.findById(actorDto.getCountry().getCountry_id());
        countryOptional.ifPresent(actorDto::setCountry);

        Actor actor = actorMapper.toEntity(actorDto);

        actorRepository.save(actor);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("Actor is successfully saved!");
        return responseMessage;
    }

    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }
}
