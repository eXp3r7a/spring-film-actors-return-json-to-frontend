package com.example.spring_form_validation_films_actors.controllers;

import com.example.spring_form_validation_films_actors.dto.FilmDTO;
import com.example.spring_form_validation_films_actors.dto.ResponseMessage;
import com.example.spring_form_validation_films_actors.entities.Film;
import com.example.spring_form_validation_films_actors.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController implements WebMvcConfigurer {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("success_added_message");
    }

    @GetMapping("/add")
    public FilmDTO addFilmForm(){
        return filmService.addFilmForm();
    }

    @PostMapping("/submit")
    public ResponseMessage submitFilmToDB(@ModelAttribute @Valid FilmDTO filmDTO, BindingResult bindingResult){
        return filmService.submitFilmToDB(filmDTO, bindingResult);
    }

    @GetMapping("/get")
    public List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }

}
