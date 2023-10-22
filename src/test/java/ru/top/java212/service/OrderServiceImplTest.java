package ru.top.java212.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.dto.OrderDTO;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.UserRepository;
import ru.top.java212.service.orders.OrderService;
import ru.top.java212.service.tools.ToolService;

import java.time.LocalDate;
import java.util.List;

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
    @Autowired
    ToolService toolService;

    @BeforeEach
    public void init(){
        User user = userRepository.findByLogin("user2");
        ToolDTO toolDTO = new ToolDTO();
        toolDTO.setName("tool2");
        toolDTO.setDistrict("District2");
        toolDTO.setStreet("Street2");
        toolDTO.setPrice("2000.0");
        toolService.save(toolDTO,user);
    }

    @Test
    public void test_that_save_is_ok(){
        OrderDTO orderDTO = new OrderDTO();
        LocalDate startDate = LocalDate.now();
        LocalDate  stopDate = startDate.plusDays(7);
        List<Tool> tools = toolService.findAll();
        int toolsSize = tools.size();
        Tool tool = tools.get(toolsSize-1);
        orderDTO.setToolId(tool.getId());
        orderDTO.setStartDate(startDate);
        orderDTO.setStopDate(stopDate);
        User thisUser = userRepository.findByLogin("user1");
        Person person = personRepository.findByUser(thisUser);
        int size = orderRepository.findAll().size();
        orderService.save(person,orderDTO);
        Assertions.assertEquals(size+1, orderRepository.findAll().size());
    }
}
