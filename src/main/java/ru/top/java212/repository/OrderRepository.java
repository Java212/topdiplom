package ru.top.java212.repository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Period;

public interface OrderRepository extends CrudRepository<Period, Integer> {
}
