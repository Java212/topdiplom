package ru.top.java212.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Order;
import ru.top.java212.model.Period;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);
    List<Order> findByStopDateLessThan(LocalDate date);
}
