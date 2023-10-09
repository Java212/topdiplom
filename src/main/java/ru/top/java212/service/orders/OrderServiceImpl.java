package ru.top.java212.service.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.repository.OrderRepository;
import java.time.LocalDate;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Boolean save(Person person, Tool tool, LocalDate startDate, LocalDate stopDate) {
        if(orderRepository.save(new Order(person,tool,startDate,stopDate)) == null){
            return true;
        }
        return false;
    }
}
