package com.example.spring_form_validation_films_actors.controllers;

import com.example.spring_form_validation_films_actors.dto.ResponseMessage;
import com.example.spring_form_validation_films_actors.entities.Country;
import com.example.spring_form_validation_films_actors.repositories.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController implements WebMvcConfigurer {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("success_added_message");
    }

    @GetMapping("/add")
    public Country addCountryForm(){
        return new Country();
    }

    @PostMapping("/submit")
    public ResponseMessage submitCountryToDB(@ModelAttribute @Valid Country country, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            addCountryForm();
        }

        countryRepository.save(country);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setMessage("Country is successfully added!");
        return responseMessage;
    }

    @GetMapping("/get")
    public List<Country> getAllCountries(Model model){
        return countryRepository.findAll();
    }
}
