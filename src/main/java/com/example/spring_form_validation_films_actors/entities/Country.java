package com.example.spring_form_validation_films_actors.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long country_id;

    @NotNull
    private String name;

    @NotNull
    @Min(1000000)
    private String numberOfCitizens;

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberOfCitizens() {
        return numberOfCitizens;
    }

    public void setNumberOfCitizens(String numberOfCitizens) {
        this.numberOfCitizens = numberOfCitizens;
    }
}
