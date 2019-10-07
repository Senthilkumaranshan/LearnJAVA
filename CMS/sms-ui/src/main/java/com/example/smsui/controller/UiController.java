package com.example.smsui.controller;

//import com.example.springdatajpaexample.model.Student;
import com.example.emsdatajpa.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableOAuth2Sso
public class UiController extends WebSecurityConfigurerAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    String name = "";
    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
    public String loadReport(ModelMap model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            name = ((UserDetails)principal).getUsername();
        } else {
            name = principal.toString();
        }

        model.addAttribute("name", name);

        return "home";
    }

    @RequestMapping(value = "/employees")
    public String loadEmployees(Model model){

        //Need to call profile service here

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> studentHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employees",
                    HttpMethod.GET,studentHttpEntity,Employee[].class);

            model.addAttribute("employees",responseEntity.getBody());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "employee";
    }

    @RequestMapping(value = "/menu")
    public String loadReport(){

        return "menu";

    }
}
