package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.Survey;
import ru.top.java212.model.SurveyResult;
import ru.top.java212.model.User;

import java.util.List;

@Repository
public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    List<SurveyResult> findByUserAndSurvey(User user, Survey survey);
}
