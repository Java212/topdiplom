package ru.top.java212.dao;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
