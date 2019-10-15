package com.example.emsdatajpa.service;

import com.example.emsdatajpa.model.Project;
import com.example.emsdatajpa.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl {

    @Autowired
    ProjectRepository projectRepository;

    public List<Project> getProjects() {

        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Integer id) {

        return projectRepository.findById(id);
    }

    public void save(Project project) {
        projectRepository.save(project);
    }
}
