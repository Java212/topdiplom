package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Tool;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool,Long> {
    List<Tool> findByTitle(String title);
}
