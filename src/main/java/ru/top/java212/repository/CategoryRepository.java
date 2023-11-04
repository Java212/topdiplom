package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByTitle(String title);
}
