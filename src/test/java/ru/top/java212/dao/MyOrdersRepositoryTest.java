package ru.top.java212.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.top.java212.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MyOrdersRepositoryTest {

    private List<Order> orders = new ArrayList<>();
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    MyOrdersRepository myOrdersRepository;

    @Test
    void test_save_find_by_my_orders() {

        LocalDate startLocalDate = LocalDate.now();
        LocalDate endLocalDate = LocalDate.of(2023, 9, 12);
        User user = userRepository.save(new User(1, "user", "pass", "user@mail.com",
                new Address(1, "District", "street", 1, 2)));
        Product product = productRepository.save( new Product(1, new Category(1, "Пневмоинструмент"),
                new Address(1, "District", "street", 1, 2),
                "productTitle", "www.link.ru", "productSpecification",
                new BigDecimal(123.0)));
        orders.add(new Order(1, user, product, startLocalDate, endLocalDate));

        MyOrders myOrders = myOrdersRepository.save(new MyOrders(1, user, orders));

        MyOrders actualMyOrders = myOrdersRepository.getReferenceById(myOrders.getId());

        assertThat(myOrders).isNotNull();
        assertThat(myOrders.getId()).isGreaterThan(0);
        assertThat(actualMyOrders).isEqualTo(myOrders);


    }

}
