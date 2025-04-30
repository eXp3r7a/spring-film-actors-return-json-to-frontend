package com.example.spring_form_validation_films_actors;
import com.example.spring_form_validation_films_actors.dto.FilmDTO;
import com.example.spring_form_validation_films_actors.entities.Film;
import com.example.spring_form_validation_films_actors.repositories.FilmRepository;
import com.example.spring_form_validation_films_actors.repositories.ActorRepository;


import com.example.spring_form_validation_films_actors.services.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {
    @InjectMocks
    FilmService filmService;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Mock
    FilmRepository filmRepository;

    @Mock
    ActorRepository actorRepository;

    @Test
    void testSubmitFilmWhenErrors(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        Film film = new Film();
        FilmDTO filmDTO = new FilmDTO();

        //WHEN
        filmService.submitFilmToDB(filmDTO,bindingResult);

        //THEN
        verify(model, times(2)).addAttribute(anyString(), any());
        verify(filmRepository, never()).save(any());
    }

    @Test
    void testSubmitFilmWhenValid(){
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        Film film = new Film();
        FilmDTO filmDTO = new FilmDTO();

        //WHEN
        filmService.submitFilmToDB(filmDTO,bindingResult);

        //THEN
        verify(model, never()).addAttribute(any());
        verify(filmRepository,times(1)).save(film);

    }
}
