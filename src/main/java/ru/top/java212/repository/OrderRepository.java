package ru.top.java212.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.entity.Order;
import ru.top.java212.entity.Product;
import ru.top.java212.entity.Profile;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByProfile(Profile profile);
    Order findByProduct(Product product);
}
