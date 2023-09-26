package ru.top.java212.dao;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Subject;

import java.util.List;

public interface SubjectDao extends CrudRepository<Subject, Integer> {

    List<Subject> findAll();
}
