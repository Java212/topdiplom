package ru.top.java212.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Address;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByUser(User user);
}
