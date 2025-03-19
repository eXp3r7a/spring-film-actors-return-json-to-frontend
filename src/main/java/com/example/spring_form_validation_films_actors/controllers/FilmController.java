package com.example.spring_form_validation_films_actors.controllers;

import com.example.spring_form_validation_films_actors.dto.FilmDTO;
import com.example.spring_form_validation_films_actors.services.FilmService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
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
    public String addFilmForm(Model model){
        return filmService.addFilmForm(model);
    }

    @PostMapping("/submit")
    public String submitFilmToDB(@ModelAttribute @Valid FilmDTO filmDTO, BindingResult bindingResult, Model model){
        return filmService.submitFilmToDB(filmDTO, bindingResult, model);
    }

    @GetMapping("/get")
    public String getAllFilms(Model model){
        return filmService.getAllFilms(model);
    }

}
