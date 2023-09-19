package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.Survey;


@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Survey findByTitle(String title);
}
