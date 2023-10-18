package ru.top.java212.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);
    @Query("SELECT o FROM Order o WHERE (o.startDate  BETWEEN ?1 and ?2) OR (o.stopDate  BETWEEN ?1 and ?2)")
    List<Order> findOrdersByDateBetween(LocalDate startDate,LocalDate stopDate);


}
