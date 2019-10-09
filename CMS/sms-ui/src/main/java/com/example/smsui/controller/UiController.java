package com.example.smsui.controller;

//import com.example.springdatajpaexample.model.Student;
import com.example.emsdatajpa.model.Employee;
import com.example.emsdatajpa.model.Project;
import com.example.emsdatajpa.model.Task;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    //display all employees
    @RequestMapping(value = "/employees")
    public String loadEmployees(Model model){

        //Need to call profile service here

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);

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


    //display specific employee details
    @RequestMapping(value = "/employee/{id}")
    public String loadEmployee(@PathVariable int id, Model model){

        //Need to call profile service here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employee/"+id,
                    HttpMethod.GET,employeeHttpEntity,Employee.class);
            model.addAttribute("employee",responseEntity.getBody());
        }
        catch (HttpStatusCodeException se){
            System.out.println("test");
            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "emp_details";
    }

    //display specific employee's particular task
    @RequestMapping(value = "/employee/{id}/tasks")
    public String loadProjectTask(@PathVariable int id, Model model){

        //Need to call profile service here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employee/"+id,
                    HttpMethod.GET,employeeHttpEntity,Employee.class);
            model.addAttribute("employee",responseEntity.getBody());
        }
        catch (HttpStatusCodeException se){
            System.out.println("test");
            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "emp_task";
    }

    //display all project details
    @RequestMapping(value = "/projects")
    public String loadProject(Model model){

        //Need to call profile service here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Project> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange("http://localhost:8980/proj/projects/",
                    HttpMethod.GET,employeeHttpEntity,Project[].class);
            model.addAttribute("projects",responseEntity.getBody());
        }
        catch (HttpStatusCodeException se){
            System.out.println("test");
            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "project";
    }

    //display all task details
    @RequestMapping(value = "/tasks")
    public String loadTask(Model model){

        //Need to call profile service here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Task> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Task[]> responseEntity = restTemplate.exchange("http://localhost:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntity,Task[].class);
            model.addAttribute("tasks",responseEntity.getBody());
        }
        catch (HttpStatusCodeException se){
            System.out.println("test");
            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "task";
    }

    @RequestMapping(value = "/operations")
    public String loadOperation(Model model){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);

            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://localhost:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntity,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://localhost:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntity,Task[].class);

            model.addAttribute("employees",responseEntity.getBody());
            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("tasks",responseEntityT.getBody());


        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "operation";
    }

    @RequestMapping(value = "/menu")
    public String loadReport(){

        return "menu";

    }
}
