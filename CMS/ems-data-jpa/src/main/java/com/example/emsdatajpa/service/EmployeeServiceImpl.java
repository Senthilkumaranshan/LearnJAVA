package com.example.emsdatajpa.service;

import com.example.emsdatajpa.model.Employee;
import com.example.emsdatajpa.model.Project;
import com.example.emsdatajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {

        for(Project project:employee.getProjects()){
            project.setEmployees((List<Employee>) employee);
        }

        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees() {
        
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {

        return employeeRepository.findById(id);
    }
}
