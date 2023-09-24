package ru.top.java212.dao;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Question;

public interface QuestionRepository extends CrudRepository<Question, Long> {



}
