package com.example.emsdatajpa.repository;

import com.example.emsdatajpa.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Integer> {

    @Query("SELECT data FROM Operation data WHERE data.eid=?1")
    public List<Operation> findOperationsByEmployeeId(Integer id);
}
