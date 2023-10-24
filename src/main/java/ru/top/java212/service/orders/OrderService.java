package ru.top.java212.service.orders;
import ru.top.java212.dto.OrderDTO;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    Boolean save(Order order);
    Boolean save(Person person, Tool tool, LocalDate startDate, LocalDate stopDate);
    Boolean save(Person person, OrderDTO orderDTO);
    List<Order> findByPerson(Person person);
    Order findOrderById(int id);
    List<Order> findStoppedByPerson(Person person);
    List<Order> findCurrentByPerson(Person person);
}
