package com.example.emsdatajpa.service;

import com.example.emsdatajpa.model.Task;
import com.example.emsdatajpa.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl {

    @Autowired
    TaskRepository taskRepository;


    public List<Task> getTasks() {

       return taskRepository.findAll();
    }
}
