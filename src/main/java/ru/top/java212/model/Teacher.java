package ru.top.java212.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table ( name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id")
    private Integer id;

    @Column ( name = "teacher_fio")
    private String fio;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private Set<Subject> subjects;

    public Teacher() {
    }

    public Teacher(String fio) {
        this.fio = fio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
