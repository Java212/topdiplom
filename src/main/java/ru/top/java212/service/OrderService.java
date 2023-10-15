package ru.top.java212.service;

import ru.top.java212.dto.OrderDto;

public interface OrderService {
     boolean save(OrderDto orderDto);
    boolean delete(Integer id);
}
