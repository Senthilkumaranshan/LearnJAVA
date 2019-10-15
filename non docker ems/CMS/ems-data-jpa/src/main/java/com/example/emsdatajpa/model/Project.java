package com.example.emsdatajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer pid;

    String pname;
//
//    @ManyToMany(mappedBy = "projects")
//    @JsonIgnore
//    List<Employee> employees;
//
//    @ManyToMany
//    @JoinTable(
//            name = "project_task",
//            joinColumns = @JoinColumn(name = "pid"),
//            inverseJoinColumns = @JoinColumn(name = "tid"))
//    private List<Task> tasks;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }

    @Override
    public String toString() {
        return "Project Id: " + this.getPid() +
                ", Project Name: " + this.getPname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(pid, project.pid) &&
                Objects.equals(pname, project.pname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, pname);
    }
}
