package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;

import java.util.List;


public interface ToolRepository extends JpaRepository<Tool,Integer> {
      List<Tool> findByPerson(Person person);
}
