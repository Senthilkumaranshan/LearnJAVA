package com.example.smsui.controller;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableOAuth2Sso
public class UiController extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @RequestMapping(value = "/")
    public String loadHome(){

        return "index";
    }

    @RequestMapping(value = "/report")
    public String loadReport(){

        return "home";
    }
}
