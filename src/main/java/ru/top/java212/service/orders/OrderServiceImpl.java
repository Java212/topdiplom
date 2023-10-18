package ru.top.java212.service.orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.top.java212.dto.OrderDTO;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.ToolRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ToolRepository toolRepository;

    @Override
    public Boolean save(Person person, Tool tool, LocalDate startDate, LocalDate stopDate) {
        try{
            Order newOrder = new Order(person,tool,startDate,stopDate);
            if(orderRepository.save(newOrder).equals( null)){
                return true;
            }
            return false;
        }catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

    @Override
    public Boolean save(Person person, OrderDTO orderDTO) {
        Tool tool = toolRepository.getReferenceById(orderDTO.getToolId());
       return this.save(person,tool,orderDTO.getStartDate(),orderDTO.getStopDate());
    }

    @Override
    public List<Order> findByPerson(Person person) {
        return orderRepository.findByPerson(person);
    }


}
