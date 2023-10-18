package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Product;
import ru.top.java212.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    Product findByTitle(String title);

    List<Product> findByIsBusy(boolean isBusy);

    List<Product> findAllByUserInfo(UserInfo userInfo);

    List<Product> findAllByTitle(String title);

    List<Product> findAll();


    default List<Product> getProductsByCategory(int categoryId) {

        List<Product> result = new ArrayList<>();
        findByIsBusy(false).iterator().forEachRemaining(result::add);
        return result.stream().filter(product -> product.getCategories().getId() == categoryId).collect(Collectors.toList());
    }

}
