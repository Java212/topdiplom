package ru.top.java212.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.top.java212.dao.OrderRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dto.OrderDto;
import ru.top.java212.model.Order;
import ru.top.java212.model.Product;
import ru.top.java212.model.UserInfo;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private ProductRepository productRepository;

    @Test
    void test_save() {
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
        when(orderRepository.save(any())).thenReturn(new Order());
        assertTrue(orderServiceImpl.save(new OrderDto()));
        verify(productRepository).findById(any());
        verify(orderRepository).save(any());
    }



    @Test
    void test_delete() {
        doNothing().when(orderRepository).deleteById(any());
        UserInfo userInfo = new UserInfo();
        when(orderRepository.findById(any())).thenReturn(
                Optional.of(new Order(userInfo, new Product(), LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L))));
        assertTrue(orderServiceImpl.delete(1));
        verify(orderRepository, atLeast(1)).findById(any());
        verify(orderRepository).deleteById(any());
    }


    @Test
    void test_delete_2() {
        doNothing().when(orderRepository).deleteById(any());
        when(orderRepository.findById(any())).thenReturn(Optional.empty());
        assertFalse(orderServiceImpl.delete(1));
        verify(orderRepository).findById(any());
    }


    @Test
    void test_find_by_list_product() {
        when(orderRepository.findByProduct(any())).thenReturn(new Order());

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        assertEquals(1, orderServiceImpl.findByListProduct(productList).size());
        verify(orderRepository).findByProduct(any());
    }


    @Test
    void test_find_by_list_product_2() {
        when(orderRepository.findByProduct(any())).thenReturn(null);

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product());
        assertTrue(orderServiceImpl.findByListProduct(productList).isEmpty());
        verify(orderRepository).findByProduct(any());
    }
}

