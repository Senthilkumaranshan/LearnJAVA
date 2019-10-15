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
    }

    @RequestMapping(value = "/")
    public String loadHome(){

        return "index";
    }


    //display all employees
    @RequestMapping(value = "/employees")
    public String loadEmployees(Model model){

        //Need to call profile service here

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);

            model.addAttribute("employees",responseEntity.getBody());
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
    @RequestMapping(value = "/employee/{id}")
    public String loadEmployee(@PathVariable Integer id, Model model){

        //Need to call profile service here
        Set<Integer> filteredpid = new HashSet<>();
        Set<Integer> filteredtid = new HashSet<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        HttpEntity<Employee> employeeHttpEntityEs = new HttpEntity<>(httpHeaders);
        HttpEntity<Operation> employeeHttpEntityO = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);
            ResponseEntity<Employee> responseEntityEs = restTemplate.exchange("http://dockerems:8980/ems/employee/"+id,
                    HttpMethod.GET,employeeHttpEntityEs,Employee.class);
            ResponseEntity<Operation[]> responseEntityO = restTemplate.exchange("http://dockerems:8980/op/operations/"+id,
                    HttpMethod.GET,employeeHttpEntityO,Operation[].class);


            for(Operation op:responseEntityO.getBody()){

               filteredpid.add(op.getPid());
                filteredtid.add(op.getTid());
            }
//            System.out.println(filteredoperations.stream().distinct().collect(Collectors.toList()).toString());

//            System.out.println(Arrays.toString(filteredoperations.toArray()));


            model.addAttribute("employees",responseEntity.getBody());
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

    //project details
    @RequestMapping(value = "/project/{id}")
    public String loadProjectDetails(@PathVariable Integer id, Model model){


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Project> employeeHttpEntityPs = new HttpEntity<>(httpHeaders);
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Project> responseEntityPs = restTemplate.exchange("http://dockerems:8980/proj/projects/"+id,
                    HttpMethod.GET,employeeHttpEntityPs,Project.class);
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);

            model.addAttribute("employees",responseEntity.getBody());
            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("tasks",responseEntityT.getBody());
            model.addAttribute("opob", new OperationObject());

            model.addAttribute("project",responseEntityPs.getBody());
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

    //project details
    @RequestMapping(value = "/tasks/{id}")
    public String loadTaskDetails(@PathVariable Integer id, Model model){


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Task> employeeHttpEntityTs = new HttpEntity<>(httpHeaders);
        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);

        try{
            ResponseEntity<Task> responseEntityTs = restTemplate.exchange("http://dockerems:8980/task/task/"+id,
                    HttpMethod.GET,employeeHttpEntityTs,Task.class);
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);

            model.addAttribute("employees",responseEntity.getBody());
            model.addAttribute("projects",responseEntityP.getBody());
            model.addAttribute("tasks",responseEntityT.getBody());
            model.addAttribute("opob", new OperationObject());

            model.addAttribute("task",responseEntityTs.getBody());
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

    //display specific employee's particular task
    @RequestMapping(value = "/employee/{id}/tasks")
    public String loadProjectTask(@PathVariable int id, Model model){

        //Need to call profile service here
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employee/"+id,
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
            ResponseEntity<Project[]> responseEntity = restTemplate.exchange("http://dockerems:8980/proj/projects/",
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
            ResponseEntity<Task[]> responseEntity = restTemplate.exchange("http://dockerems:8980/task/tasks/",
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
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);

            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
                    HttpMethod.GET,employeeHttpEntityT,Task[].class);

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

    @RequestMapping(value = "/operation")
    public String saveOperation(@ModelAttribute OperationObject operationobj){

        Operation operation = new Operation();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        for(Integer i=0;i<operationobj.getTid().length;i++){

            operation.setEid(operationobj.getEid());
            operation.setPid(operationobj.getPid());
            operation.setTid(operationobj.getTid()[i]);

            HttpEntity<Operation> employeeHttpEntityO = new HttpEntity<>(operation,httpHeaders);
            restTemplate.postForEntity("http://dockerems:8980/op/operations/save",
                    employeeHttpEntityO,String.class);
        }

        return "redirect:/employee/"+operationobj.getEid();

    }

    @RequestMapping(value = "/employee")
    public String saveEmployee(@ModelAttribute Employee employee){

        System.out.println(employee.toString());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());



            HttpEntity<Employee> employeeHttpEntityE = new HttpEntity<>(employee,httpHeaders);
            restTemplate.postForEntity("http://dockerems:8980/ems/employee",
                    employeeHttpEntityE,String.class);


        return "redirect:/menu";

    }

    @RequestMapping(value = "/project")
    public String saveProject(@ModelAttribute Project project){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());



        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(project,httpHeaders);
        restTemplate.postForEntity("http://dockerems:8980/proj/project",
                employeeHttpEntityP,String.class);


        return "redirect:/menu";

    }

    @RequestMapping(value = "/task")
    public String saveTask(@ModelAttribute Task task){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());



        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(task,httpHeaders);
        restTemplate.postForEntity("http://dockerems:8980/task/task",
                employeeHttpEntityT,String.class);


        return "redirect:/menu";

    }

    @RequestMapping(value = "/menu")
    public String loadReport(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks",
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

    @RequestMapping(value = "/employee-tab")
    public String loadEmployeetab(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
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
        return "redirect:/menu";

    }

    @RequestMapping(value = "/project-tab")
    public String loadProjecttab(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
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
        return "redirect:/menu";

    }

    @RequestMapping(value = "/task-tab")
    public String loadTasktab(Model model){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization",AccessTokenConfig.getToken());

        HttpEntity<Employee> employeeHttpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Project> employeeHttpEntityP = new HttpEntity<>(httpHeaders);
        HttpEntity<Task> employeeHttpEntityT = new HttpEntity<>(httpHeaders);
        try{
            ResponseEntity<Employee[]> responseEntity = restTemplate.exchange("http://dockerems:8980/ems/employees",
                    HttpMethod.GET,employeeHttpEntity,Employee[].class);
            ResponseEntity<Project[]> responseEntityP = restTemplate.exchange("http://dockerems:8980/proj/projects",
                    HttpMethod.GET,employeeHttpEntityP,Project[].class);

            ResponseEntity<Task[]> responseEntityT = restTemplate.exchange("http://dockerems:8980/task/tasks/",
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
        return "redirect:/menu";

    }



}
