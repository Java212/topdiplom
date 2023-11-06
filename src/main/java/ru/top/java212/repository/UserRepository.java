package ru.top.java212.repository;


import org.springframework.data.repository.CrudRepository;
import ru.top.java212.entity.User;

public interface UserRepository  extends CrudRepository<User, Integer> {
    User findByUserName(String username);
}
