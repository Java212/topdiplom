package ru.top.java212.repository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Address;
import ru.top.java212.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
