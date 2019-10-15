package com.example.emsdatajpa.controller;

import com.example.emsdatajpa.model.Project;
import com.example.emsdatajpa.service.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/project",method = RequestMethod.POST)
    public void save(@RequestBody Project project){

         projectService.save(project);
    }

    @RequestMapping(value = "/projects/{id}",method = RequestMethod.GET)
    public Optional<Project> getProjectById(@PathVariable Integer id){
        return projectService.getProjectById(id);
    }
}
