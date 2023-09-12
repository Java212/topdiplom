package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ru.top.java212.model.MyOrders;

public interface MyOrdersRepository extends JpaRepository<MyOrders, Integer> {
}
