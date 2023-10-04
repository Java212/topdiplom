package ru.top.java212.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ToolRepository extends JpaRepository<Tool,Integer> {
      List<Tool> findByPerson(Person person);
      List<Tool> findByName(String name);
      List<Tool> findByPriceBetween(Double priceMin,Double PriceMax);
}
