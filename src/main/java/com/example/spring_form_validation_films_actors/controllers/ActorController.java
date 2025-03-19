package com.example.spring_form_validation_films_actors.controllers;

import com.example.spring_form_validation_films_actors.dto.ActorDTO;
import com.example.spring_form_validation_films_actors.services.ActorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
@RequestMapping("/actors")
public class ActorController implements WebMvcConfigurer {

    private final ActorService actorService;

    public ActorController(ActorService actorService){
        this.actorService = actorService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("success_added_message");
    }

    @GetMapping("/add")
    public String addActorForm(Model model){
        return actorService.addActorForm(model);
    }

    @PostMapping("/submit")
    public String submitActorToDB(@Valid ActorDTO actorDto, BindingResult bindingResult, Model model){
        return actorService.submitActorToDB(actorDto, bindingResult, model);
    }

    @GetMapping("/get")
    public String getAllActors(Model model){
        return actorService.getAllActors(model);
    }
}
