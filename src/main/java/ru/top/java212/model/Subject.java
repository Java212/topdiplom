package ru.top.java212.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "subjects")

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer teacher_id;

    public Subject(Integer id, String name, Integer teacher_id) {
        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
    }

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(id, subject.id) && Objects.equals(name, subject.name) && Objects.equals(teacher_id, subject.teacher_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teacher_id);
    }
}


