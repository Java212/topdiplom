package ru.top.java212.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dto.OrderDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.UserRepository;
import ru.top.java212.service.orders.OrderService;

import java.time.LocalDate;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @Test
    public void test_that_save_is_ok(){
        OrderDTO orderDTO = new OrderDTO();
        LocalDate startDate = LocalDate.now();
        LocalDate  stopDate = startDate.plusDays(7);
        orderDTO.setToolId(1);
        orderDTO.setStartDate(startDate);
        orderDTO.setStopDate(stopDate);
        User thisUser = userRepository.findByLogin("user1");
        Person person = personRepository.findByUser(thisUser);
        int size = orderRepository.findAll().size();
        orderService.save(person,orderDTO);
        Assertions.assertEquals(size+1, orderRepository.findAll().size());
    }
}
