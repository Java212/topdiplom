package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import ru.top.java212.model.Subject;


import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Subject findByTitle(String title);

    @Query(value = "SELECT subjects.subject_id, subjects.title, teachers.teacher_id, teachers.name, teachers.surname, teachers.patronymic\n" +
            "FROM subjects\n" +
            "JOIN teachers ON subjects.teacher_id = teachers.teacher_id", nativeQuery = true)
    List<Subject> findAllNative();


}
