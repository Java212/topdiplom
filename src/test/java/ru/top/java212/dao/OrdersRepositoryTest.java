package ru.top.java212.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.top.java212.model.*;
import java.time.LocalDate;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class OrdersRepositoryTest {


   @Autowired
   OrdersRepository ordersRepository;


    @Test
    void test_save_find_by_my_orders() {

        LocalDate startLocalDate = LocalDate.now();
        LocalDate endLocalDate = LocalDate.of(2023, 9, 12);

    Order order = ordersRepository.save(new Order(1, new UserInfo("user", "user@mail.com","1234-43535",new User(),new Address()),new Product(), startLocalDate, endLocalDate));



        Order actualOrders = ordersRepository.getReferenceById(order.getId());

        assertThat(order).isNotNull();
        assertThat(order.getId()).isGreaterThan(0);
        assertThat(actualOrders).isEqualTo(order);


    }

}
