package ru.top.java212.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Order;
import ru.top.java212.model.Period;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
