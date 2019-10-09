package com.example.emsdatajpa.controller;

import com.example.emsdatajpa.model.Task;
import com.example.emsdatajpa.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    TaskServiceImpl taskService;

    @RequestMapping(value = "/tasks",method = RequestMethod.GET)
    public List<Task> getTasks(){

        return taskService.getTasks();
    }
}
