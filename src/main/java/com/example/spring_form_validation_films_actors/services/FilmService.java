package com.example.spring_form_validation_films_actors.services;

import com.example.spring_form_validation_films_actors.dto.FilmDTO;
import com.example.spring_form_validation_films_actors.entities.Actor;
import com.example.spring_form_validation_films_actors.entities.Film;
import com.example.spring_form_validation_films_actors.mappers.FilmMapper;
import com.example.spring_form_validation_films_actors.repositories.ActorRepository;
import com.example.spring_form_validation_films_actors.repositories.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class FilmService {

    private final ActorRepository actorRepository;

    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    public FilmService(ActorRepository actorRepository, FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmMapper = filmMapper;
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
    }

    public String addFilmForm(Model model){
        FilmDTO filmDTO = new FilmDTO();
        for (int i = 0; i < 4; i++) {
            filmDTO.getActors().add(new Actor());
        }
        model.addAttribute("film", filmDTO);
        model.addAttribute("actors", actorRepository.findAll());

        return "films/add_form";
    }

    public String submitFilmToDB(@ModelAttribute @Valid FilmDTO filmDTO, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("film", filmDTO);
            model.addAttribute("actors", actorRepository.findAll());

            return "films/add_form";
        }

        if(!compareTitles(filmDTO.getTitle(),filmDTO.getTitleChecker())){
            model.addAttribute("film", filmDTO);
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("titleCheckerError", "Title is not equal. Please validate title!");

            return "films/add_form";
        }

        Film film = filmMapper.toEntity(filmDTO);
        filmRepository.save(film);
        return "redirect:/results";
    }

    public String getAllFilms(Model model){
        model.addAttribute("films", filmRepository.findAll());

        return "films/get_films";
    }

    private boolean compareTitles(String title, String titleChecker){
        return title.equals(titleChecker);
    }
}
