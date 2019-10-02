package com.example.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this is a mvc based so we use rest controller
@RequestMapping("rest/index")  //when we go to this url it will say hello world
public class IndexController {

    @GetMapping
    public String random(){
        return "hello world";
    }


}
