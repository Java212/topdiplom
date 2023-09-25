package ru.top.java212.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

}
