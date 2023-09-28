package ru.top.java212;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectsH2Dao implements SubjectsDAO{
    private final EntityManager entityManager;

    @Autowired
    public SubjectsH2Dao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Subject> getSubjects() {
        String jpql = "SELECT n FROM Subject n";
        TypedQuery<Subject> query = entityManager.createQuery(jpql, Subject.class);
        return query.getResultList();
    }
}
