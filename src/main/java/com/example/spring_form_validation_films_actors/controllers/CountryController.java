package com.example.spring_form_validation_films_actors.controllers;

import com.example.spring_form_validation_films_actors.entities.Country;
import com.example.spring_form_validation_films_actors.repositories.CountryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.ui.Model;

@Controller
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
    public String addCountryForm(Model model){
        model.addAttribute("country", new Country());

        return "countries/add_form";
    }

    @PostMapping("/submit")
    public String submitCountryToDB(@ModelAttribute @Valid Country country, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "countries/add_form";
        }

        countryRepository.save(country);
        return "redirect:/results";
    }

    @GetMapping("/get")
    public String getAllCountries(Model model){
        model.addAttribute("countries", countryRepository.findAll());

        return "countries/get_countries";
    }
}
