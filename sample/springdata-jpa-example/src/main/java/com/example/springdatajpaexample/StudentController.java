package com.example.springdatajpaexample;

import com.example.springdatajpaexample.model.Address;
import com.example.springdatajpaexample.model.Student;
import com.example.springdatajpaexample.model.Telephone;
import com.example.springdatajpaexample.service.studentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sms")
public class StudentController {


    @Autowired
    studentServiceImpl studentService;


    @RequestMapping(value = "/hello")
    public String greetings(){
        return "Hello Springroot";
    }


    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public Student getStudent(){

        Student student = new Student();
        student.setName("Saman");
        Address address = new Address();
        address.setCity("Galle");
        student.setAddress(address);

        List <Telephone>  telephones = new ArrayList<>();
        Telephone telephone = new Telephone();
        telephone.setNumber("045454535353");
        telephones.add(telephone);
        telephone.setStudent(student);

        student.setTelephones(telephones);

        return student;
    }

    @RequestMapping(value = "/student",method = RequestMethod.POST)
    public Student save(@RequestBody Student student){

        return studentService.save(student);

    }

    @RequestMapping(value = "/allstudent",method = RequestMethod.GET)
    public List<Student> getStudents(){

        return studentService.getStudents();
    }

    @RequestMapping(value = "/find/{id}",method = RequestMethod.GET)
    public Optional<Student> getStudentById(@PathVariable int id){

        return studentService.getStudentById(id);
    }

}
