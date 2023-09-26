package ru.top.java212.model;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name="teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;
    String name;
    @OneToMany(mappedBy = "teacher")
    List<Subject> subjects = new LinkedList<>();
    Teacher(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
