package com.example.spring_form_validation_films_actors.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
