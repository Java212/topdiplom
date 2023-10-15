package ru.top.java212.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Order;
import ru.top.java212.model.UserInfo;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserInfo(UserInfo userInfo);
}
