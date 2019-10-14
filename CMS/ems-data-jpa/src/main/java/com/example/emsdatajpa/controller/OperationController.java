package com.example.emsdatajpa.controller;

import com.example.emsdatajpa.model.Operation;
import com.example.emsdatajpa.service.OperationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/op")
public class OperationController {

    @Autowired
    OperationServiceImpl operationService;
    @RequestMapping(value = "/operations/{id}",method = RequestMethod.GET)
    public List<Operation> getOperations(@PathVariable Integer id){
        return operationService.getOperationById(id);
    }
}
