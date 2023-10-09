package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Order;

public interface OrdersRepository extends JpaRepository<Order, Integer> {
}
