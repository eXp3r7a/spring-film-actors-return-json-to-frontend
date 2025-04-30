package com.example.spring_form_validation_films_actors.services;

import com.example.spring_form_validation_films_actors.dto.FilmDTO;
import com.example.spring_form_validation_films_actors.dto.ResponseMessage;
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

import java.util.List;

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

    public FilmDTO addFilmForm(){
        FilmDTO filmDTO = new FilmDTO();
        for (int i = 0; i < 4; i++) {
            filmDTO.getActors().add(new Actor());
        }
        filmDTO.setAllActorsList(actorRepository.findAll());
        return filmDTO;
    }

    public ResponseMessage submitFilmToDB(@ModelAttribute @Valid FilmDTO filmDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            addFilmForm();
        }

        if(!compareTitles(filmDTO.getTitle(),filmDTO.getTitleChecker())){
            //model.addAttribute("titleCheckerError", "Title is not equal. Please validate title!");
            addFilmForm();
        }

        Film film = filmMapper.toEntity(filmDTO);
        filmRepository.save(film);

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("Film is successfully saved!");
        return responseMessage;
    }

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    private boolean compareTitles(String title, String titleChecker){
        return title.equals(titleChecker);
    }
}
