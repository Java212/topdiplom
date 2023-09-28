package ru.top.java212.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Subject;

import java.util.List;


public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    List<Subject> findByTeacherNotNull();

}
