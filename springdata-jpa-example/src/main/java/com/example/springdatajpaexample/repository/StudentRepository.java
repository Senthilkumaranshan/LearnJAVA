package com.example.springdatajpaexample.repository;

import com.example.springdatajpaexample.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
