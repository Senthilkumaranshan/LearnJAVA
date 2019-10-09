package com.example.emsdatajpa.repository;

import com.example.emsdatajpa.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Integer> {
}
