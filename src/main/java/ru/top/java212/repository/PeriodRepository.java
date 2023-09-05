package ru.top.java212.repository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Address;
import ru.top.java212.model.Period;

public interface PeriodRepository extends CrudRepository<Period, Integer> {
}
