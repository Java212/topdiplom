package ru.top.java212;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.model.Teacher;

import java.util.List;

@Component
public class TeachersH2DAO implements TeachersDAO{
    private final EntityManager entityManager;

    @Autowired
    public TeachersH2DAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Teacher> getTeachers() {
        String jpql = "SELECT n FROM Teacher n";
        TypedQuery<Teacher> query = entityManager.createQuery(jpql, Teacher.class);
        return query.getResultList();
    }
}