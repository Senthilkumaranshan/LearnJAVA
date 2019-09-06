package com.example.springdatajpaexample;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sms")
public class StudentController {

    @RequestMapping(value = "/hello")
    public String greetings(){
        return "Hello Springroot";
    }

}
