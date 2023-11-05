package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findByName(String name);
}
