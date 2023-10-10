package ru.top.java212.service.orders;
import ru.top.java212.dto.OrderDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import java.time.LocalDate;

public interface OrderService {
    Boolean save(Person person, Tool tool, LocalDate startDate, LocalDate stopDate);
    public Boolean save(Person person, OrderDTO orderDTO);
}
