package com.example.emsdatajpa.repository;

import com.example.emsdatajpa.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
}
