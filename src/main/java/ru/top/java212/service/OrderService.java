package ru.top.java212.service;

import ru.top.java212.dto.OrderDto;
import ru.top.java212.entity.Order;
import ru.top.java212.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean save(OrderDto orderDto);
    boolean delete(Integer id);
    Map<Product, Order> findByListProduct(List<Product> products);
}
