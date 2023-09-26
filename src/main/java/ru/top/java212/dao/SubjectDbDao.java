package ru.top.java212.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Subject;

import java.util.List;

public interface SubjectDbDao extends CrudRepository<Subject, Integer> {

    List<Subject> findAll();

    @Query("select subjectName from Subject where teacher_id not null")
    List<Subject> getSubjectsWithTeachers();
}
