package com.example.spring_form_validation_films_actors.services;

import com.example.spring_form_validation_films_actors.dto.ActorDTO;
import com.example.spring_form_validation_films_actors.entities.Actor;
import com.example.spring_form_validation_films_actors.entities.Country;
import com.example.spring_form_validation_films_actors.mappers.ActorMapper;
import com.example.spring_form_validation_films_actors.repositories.ActorRepository;
import com.example.spring_form_validation_films_actors.repositories.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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

    public String addActorForm(Model model) {
        model.addAttribute("actor", new ActorDTO());
        model.addAttribute("countries", countryRepository.findAll());

        return "actors/add_form";
    }

    public String submitActorToDB(@Valid ActorDTO actorDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("actor", actorDto);
            model.addAttribute("countries", countryRepository.findAll());

            return "actors/add_form";
        }


        Optional<Country> countryOptional = countryRepository.findById(actorDto.getCountry().getCountry_id());
        if (countryOptional.isPresent()) {
            actorDto.setCountry(countryOptional.get());
        }

        Actor actor = actorMapper.toEntity(actorDto);

        actorRepository.save(actor);
        return "redirect:/results";
    }

    public String getAllActors(Model model) {
        model.addAttribute("actors", actorRepository.findAll());

        return "actors/get_actors";
    }
}
