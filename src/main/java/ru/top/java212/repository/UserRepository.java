package ru.top.java212.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.top.java212.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByName(String name);
}
