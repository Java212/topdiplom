package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByTitle(String title);

}
