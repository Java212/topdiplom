package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Product findByTitle(String title);

    List<Product> findAll();


    default List<Product> getProductsByCategory(int categoryId) {

        List<Product> result = new ArrayList<>();
        findAll().iterator().forEachRemaining(result::add);
        return result.stream().filter(product -> product.getCategories().getId() == categoryId).collect(Collectors.toList());
    }

}
