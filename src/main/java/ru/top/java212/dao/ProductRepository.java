package ru.top.java212.dao;


import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByTitle(String title);

}
