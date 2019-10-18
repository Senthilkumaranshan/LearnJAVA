package com.example.smsui.controller;


import com.example.smsui.model.*;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

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
                .antMatchers("/","/images/**","/css/**","/js/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").deleteCookies("KSESSION","JSESSIONID").invalidateHttpSession(true).clearAuthentication(true);
        http.csrf().disable();
    }

    @RequestMapping(value = "/")
    public String loadHome(){

        return "index";
    }


    //refactor

    @RequestMapping(value = "/menu")
    public String loadReport(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://localhost:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://localhost:8980/task/tasks",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);

            model.addAttribute("employees",responseEntity.getBody());
            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("tasks",responseEntityT.getBody());
            model.addAttribute("opob", new OperationObject());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "mainmenu";

    }
/* -------------------------------------------------------------------------------------------------------------------------*/
    //display list of employees
    @RequestMapping(value = "em/employee")
    public String loadAllEmployees(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);

            model.addAttribute("employees",responseEntity.getBody());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "allemployee";

    }

    //display specific employee details
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public String loadEmployee(@PathVariable Integer id, Model model){

        //Need to call profile service here
        Set<Integer> filteredpid = new HashSet<>();
        Set<Integer> filteredtid = new HashSet<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        HttpEntity<Employee> employeeHttpEntityEs = new HttpEntity<>(httpHeaders);
        HttpEntity<Operation> employeeHttpEntityO = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://localhost:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://localhost:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);
            ResponseEntity<Employee> responseEntityEs = restTemplate.exchange("http://localhost:8980/ems/employee/"+id,
                    HttpMethod.GET,employeeHttpEntityEs,Employee.class);
            ResponseEntity<Operation[]> responseEntityO = restTemplate.exchange("http://localhost:8980/op/operations/"+id,
                    HttpMethod.GET,employeeHttpEntityO,Operation[].class);


            for(Operation op:responseEntityO.getBody()){

                filteredpid.add(op.getPid());
                filteredtid.add(op.getTid());
            }
            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("tasks",responseEntityT.getBody());
            model.addAttribute("employee",responseEntityEs.getBody());
            model.addAttribute("operations",responseEntityO.getBody());
            model.addAttribute("filteredpid",filteredpid);
            model.addAttribute("filteredtid",filteredtid);
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "emp_details";
    }

    @RequestMapping(value = "/employee")
    public String saveEmployee(@ModelAttribute Employee employee){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());



        HttpEntity<Employee> employeeHttpEntityE = new HttpEntity<>(employee,httpHeaders);
        restTemplate.postForEntity("http://localhost:8980/ems/employee",
                employeeHttpEntityE,String.class);


        return "redirect:/em/employee";

    }

//    @RequestMapping(value = "/employee",method = RequestMethod.POST)
//    public ResponseEntity<?> saveEmployee(@RequestParam("name") String name,Employee employee){
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        System.out.println(name);
//        employee.setEname(name);
//
//        httpHeaders.add("Authorization",AccessTokenConfig.getToken());
//
//        HttpEntity<Employee> employeeHttpEntityE = new HttpEntity<>(employee,httpHeaders);
//
//        restTemplate.postForEntity("http://localhost:8980/ems/employee",
//                employeeHttpEntityE,String.class);
//
//        return ResponseEntity.ok("success");
//
//
//    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id, Model model){

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntityE = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employee/"+id,
                HttpMethod.DELETE,employeeHttpEntityE,String.class);

        return ResponseEntity.ok(employeeHttpEntityE.getBody());


    }

/* --------------------------------------------------------------------------------------------------------------------------*/
/*----------------------------------------------------Projects---------------------------------------------------------------*/

    @RequestMapping(value = "/pro/projects")
    public String loadProjects(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://localhost:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "allproject";

    }

    //project details
    @RequestMapping(value = "/project/{id}")
    public String loadProjectDetails(@PathVariable Integer id, Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Project> responseEntityP = restTemplate.exchange("http://localhost:8980/proj/projects/"+id,
                    HttpMethod.GET,employeeHttpEntityP,Project.class);

            model.addAttribute("project",responseEntityP.getBody());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());

        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "project_details";
    }

    @RequestMapping(value = "/project")
    public String saveProject(@ModelAttribute Project project){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(project,httpHeaders);
        restTemplate.postForEntity("http://localhost:8980/proj/project",
                employeeHttpEntityP,String.class);

        return "redirect:pro/projects";

    }

    /* --------------------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------Tasks---------------------------------------------------------------*/

    @RequestMapping(value = "/tsk/tasks")
    public String loadTasks(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://localhost:8980/task/tasks",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);

            model.addAttribute("tasks",responseEntityT.getBody());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "alltask";

    }

    @RequestMapping(value = "/tasks/{id}")
    public String loadTask(@PathVariable Integer id, Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Task> responseEntityT = restTemplate.exchange("http://localhost:8980/task/task/"+id,
                    HttpMethod.GET,employeeHttpEntityT,Task.class);

            model.addAttribute("task",responseEntityT.getBody());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());

        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }

        return "task_details";
    }

    @RequestMapping(value = "/task")
    public String saveTaskD(@ModelAttribute Task task){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(task,httpHeaders);
        restTemplate.postForEntity("http://localhost:8980/task/task",
                employeeHttpEntityT,String.class);


        return "redirect:tsk/tasks";

    }

    /* --------------------------------------------------------------------------------------------------------------------------*/
    /*----------------------------------------------------Operations---------------------------------------------------------------*/

    @RequestMapping(value = "/op/operation")
    public String loadOperations(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://localhost:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://localhost:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://localhost:8980/task/tasks",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);

            model.addAttribute("employees",responseEntity.getBody());
            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("tasks",responseEntityT.getBody());
            model.addAttribute("opob", new OperationObject());
            model.addAttribute("username",AccessTokenConfig.getPrincipalName());
            model.addAttribute("privilage",AccessTokenConfig.getAuthorities());
        }
        catch (HttpStatusCodeException se){

            ResponseEntity responseEntity = ResponseEntity.status(se.getStatusCode())
                    .headers(se.getResponseHeaders())
                    .body(se.getResponseBodyAsString());
            model.addAttribute("error",responseEntity);
        }
        return "alloperation";

    }

    @RequestMapping(value = "/operation")
    public String saveOperations(@ModelAttribute OperationObject operationobj){

        Operation operation = new Operation();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        for(Integer i=0;i<operationobj.getTid().length;i++){

            operation.setEid(operationobj.getEid());
            operation.setPid(operationobj.getPid());
            operation.setTid(operationobj.getTid()[i]);

            HttpEntity<Operation> employeeHttpEntityO = new HttpEntity<>(operation,httpHeaders);
            restTemplate.postForEntity("http://localhost:8980/op/operations/save",
                    employeeHttpEntityO,String.class);
        }

        return "redirect:/employee/"+operationobj.getEid();

    }


}
