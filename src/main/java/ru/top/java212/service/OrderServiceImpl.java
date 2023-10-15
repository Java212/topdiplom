package ru.top.java212.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.top.java212.dao.OrderRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dto.OrderDto;
import ru.top.java212.model.Order;

@Service
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public boolean save(OrderDto orderDto) {
        orderRepository.save(new Order(orderDto.getUserInfo(),
                productRepository.findById(orderDto.getProductId()).orElse(null),
                orderDto.getStartDate(), orderDto.getEndDate()));
        return true;
    }

    @Override
    public boolean delete(Integer id) {

            if (orderRepository.findById(id).isPresent()) {
                orderRepository.deleteById(id);
                return true;
            }
            return false;
        }
    }

