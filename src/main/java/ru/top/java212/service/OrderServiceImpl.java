package ru.top.java212.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.ProductRepository;
import ru.top.java212.dto.OrderDto;
import ru.top.java212.entity.Order;
import ru.top.java212.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Product product = productRepository.findById(orderDto.getProductId()).orElseThrow();
        product.setBusy(true);
        orderRepository.save(new Order(orderDto.getUserInfo(), product, orderDto.getStartDate(), orderDto.getEndDate()));
        return true;
    }
    @Override
    public boolean delete(Integer id) {
        if (orderRepository.findById(id).isPresent()) {
            Order order = orderRepository.findById(id).orElseThrow();
            Product product = order.getProduct();
            product.setBusy(false);
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
    @Override
    public Map<Product, Order> findByListProduct(List<Product> products) {
        Map<Product, Order> mapOrders = new HashMap<>();
        for (Product product : products) {
            Order order = orderRepository.findByProduct(product);
            if (order != null) {
                 mapOrders.put(product, order);
            }
        }
        return mapOrders;
    }
}

