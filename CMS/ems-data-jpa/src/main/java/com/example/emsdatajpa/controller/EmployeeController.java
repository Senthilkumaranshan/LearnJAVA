package com.example.emsdatajpa.controller;

import com.example.emsdatajpa.model.Employee;
import com.example.emsdatajpa.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ems")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @RequestMapping(value = "/employees",method = RequestMethod.POST)
    public Employee save(@RequestBody Employee employee){

        return employeeService.save(employee);

    }

    @RequestMapping(value = "/employees",method = RequestMethod.GET)
    public List<Employee> getEmployees(){

        return employeeService.getEmployees();
    }

    @RequestMapping(value = "/employee/{id}",method = RequestMethod.GET)
    public Optional<Employee> getEmployeeById(@PathVariable int id){
        return employeeService.getEmployeeById(id);
    }


}
