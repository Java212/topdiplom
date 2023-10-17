package ru.top.java212.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByPerson(Person person);
    @Query("SELECT o FROM Order o WHERE (o.startDate  NOT BETWEEN ?1 and ?2) AND (o.stopDate NOT BETWEEN ?1 and ?2)")
    List<Order> findByDateNotBetween(LocalDate startDate,LocalDate stopDate);


}
