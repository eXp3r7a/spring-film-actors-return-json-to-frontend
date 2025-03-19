package com.example.spring_form_validation_films_actors.util;

public class checkIsLeapYear {
    public boolean isLeapYear(int year){
        if(year % 4 == 0){
            return true;
        }
        else {
            return false;
        }
    }
}
