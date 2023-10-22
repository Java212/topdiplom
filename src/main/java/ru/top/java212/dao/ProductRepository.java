package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Product;
import ru.top.java212.model.UserInfo;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findFirst10ByOrderByIdDesc();

    Product findByTitle(String title);

    List<Product> findByIsBusy(boolean isBusy);

    List<Product> findAllByUserInfo(UserInfo userInfo);

    List<Product> findAllByTitle(String title);

    List<Product> findAll();




}
