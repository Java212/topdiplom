package ru.top.java212.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "teachers")

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Teacher(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Teacher() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) && Objects.equals(name, teacher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
