package com.example.emsdatajpa.service;

import com.example.emsdatajpa.model.Project;
import com.example.emsdatajpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl {

    @Autowired
    ProjectRepository projectRepository;

    public List<Project> getProjects() {

        return projectRepository.findAll();
    }
}
