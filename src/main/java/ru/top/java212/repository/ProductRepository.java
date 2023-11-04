package ru.top.java212.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.entity.Product;
import ru.top.java212.entity.Profile;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findFirst10ByOrderByIdDesc();
    Product findByTitle(String title);
    List<Product> findByIsBusy(boolean isBusy);
    List<Product> findAllByProfile(Profile profile);
    List<Product> findAllByTitle(String title);
    List<Product> findAll();
}
