package ru.top.java212.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.top.java212.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class MyOrdersRepositoryTest {
    private List<Category> categories = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    MyOrdersRepository myOrdersRepository;

    @Test
    void test_save_find_by_my_orders(){
        Address address = entityManager.persist(new Address("District", "street", 1, 2));
        User user = entityManager.persist(new User("user", "pass", "user@mail.com", address));
        Category category = entityManager.persist(new Category("Пневмоинструмент"));
        categories.add(category);
        LocalDate startLocalDate = LocalDate.now();
        LocalDate endLocalDate = LocalDate.of(2023,9,12);

        Product product = entityManager.persist(new Product(categories, address, "productTitle", "productSpecification", new BigDecimal(123.0)));
        Order order = entityManager.persist(new Order(user, product, startLocalDate,endLocalDate));
        orders.add(order);
        MyOrders myOrders = myOrdersRepository.save(new MyOrders(user, orders));

        MyOrders actualMyOrders = myOrdersRepository.getReferenceById(myOrders.getId());

        assertThat(myOrders).isNotNull();
        assertThat(myOrders.getId()).isGreaterThan(0);
        assertThat(actualMyOrders).isEqualTo(myOrders);




    }

}
