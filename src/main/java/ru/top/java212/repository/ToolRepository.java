package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Tool;


public interface ToolRepository extends JpaRepository<Tool,Integer> {

}
