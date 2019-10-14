package com.example.emsdatajpa.service;

import com.example.emsdatajpa.model.Operation;
import com.example.emsdatajpa.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl {

    @Autowired
    OperationRepository operationRepository;

    public List<Operation> getOperationById(Integer id) {

        return  operationRepository.findOperationsByEmployeeId(id);
    }
}
