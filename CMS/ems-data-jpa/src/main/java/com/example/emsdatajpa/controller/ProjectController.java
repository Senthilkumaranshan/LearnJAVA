package com.example.emsdatajpa.controller;

import com.example.emsdatajpa.model.Project;
import com.example.emsdatajpa.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/proj")
public class ProjectController {

    @Autowired
    ProjectServiceImpl projectService;

    @RequestMapping(value = "/projects",method = RequestMethod.GET)
    public List<Project> getEmployees(){

        return projectService.getProjects();
    }

    @RequestMapping(value = "/projects/{id}",method = RequestMethod.GET)
    public Optional<Project> getProjectById(@PathVariable Integer id){
        return projectService.getProjectById(id);
    }
}
