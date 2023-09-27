package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import ru.top.java212.model.Teacher;


public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findByName(String name);
}
