package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table (name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id ")
    private Integer id;

    @Column ( name = "subject_name")
    private String subjectName;

    public Subject() {
    }

    public Subject(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
