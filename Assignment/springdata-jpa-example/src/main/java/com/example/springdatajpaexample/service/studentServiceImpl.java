package com.example.springdatajpaexample.service;

import com.example.springdatajpaexample.model.Student;
import com.example.springdatajpaexample.model.Telephone;
import com.example.springdatajpaexample.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class studentServiceImpl {


    @Autowired
    StudentRepository studentRepository;


    public Student save(Student student) {

        for(Telephone telephone:student.getTelephones()){
            telephone.setStudent(student);
        }

        return studentRepository.save(student);
    }

    public List<Student> getStudents(){

        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id){

        return studentRepository.findById(id);
    }


}
