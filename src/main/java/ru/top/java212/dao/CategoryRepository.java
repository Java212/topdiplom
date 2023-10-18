package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Category findByTitle(String title);

}
