package com.example.spring_form_validation_films_actors;

import com.example.spring_form_validation_films_actors.dto.ActorDTO;
import com.example.spring_form_validation_films_actors.entities.Actor;
import com.example.spring_form_validation_films_actors.entities.Country;
import com.example.spring_form_validation_films_actors.services.ActorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import com.example.spring_form_validation_films_actors.repositories.CountryRepository;
import com.example.spring_form_validation_films_actors.repositories.ActorRepository;


import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {
    @InjectMocks
    ActorService actorService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Mock
    ActorRepository actorRepository;

    @Mock
    CountryRepository countryRepository;

    @Test
    void testSubmitActorWhenErrors(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        ActorDTO actorDTO = new ActorDTO();
        Actor actor = new Actor();

        //WHEN
        actorService.submitActorToDB(actorDTO,bindingResult, model);

        //THEN
        verify(model, times(2)).addAttribute(anyString(), any());
        verify(actorRepository, never()).save(any());
    }

    @Test
    void testSubmitActorWhenValid(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        ActorDTO actorDTO = new ActorDTO();
        Actor actor = new Actor();

        Country country = new Country();
        country.setCountry_id(1L);
        actor.setCountry(country);

        when(countryRepository.findById(country.getCountry_id())).thenReturn(Optional.of(country));

        //WHEN
        String viewName = actorService.submitActorToDB(actorDTO,bindingResult, model);

        //THEN
        verify(model, never()).addAttribute(anyString(), any());
        verify(actorRepository, times(1)).save(actor);
        Assertions.assertEquals("redirect:/results", viewName);
    }

}
