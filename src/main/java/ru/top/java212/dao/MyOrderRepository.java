package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.MyOrders;

public interface MyOrderRepository extends JpaRepository<MyOrders, Long> {
}
