package com.example.spring_form_validation_films_actors.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class UserController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/access-denied").setViewName("users/access_denied");
    }

    @GetMapping("/login")
    public String login(){
        return "users/login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "users/user_info";
    }


}
