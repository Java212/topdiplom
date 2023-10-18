package ru.top.java212.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.top.java212.dao.OrderRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dto.OrderDto;
import ru.top.java212.model.Order;
import ru.top.java212.model.Product;

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
        Product product =  productRepository.findById(orderDto.getProductId()).orElseThrow();
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
    }

