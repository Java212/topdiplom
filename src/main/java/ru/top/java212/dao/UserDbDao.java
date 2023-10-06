package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.User;

public interface UserDbDao extends JpaRepository<User, Integer> {

  User findByName(String name);

  User findByLogin(String login);
}
