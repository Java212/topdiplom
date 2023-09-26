package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
