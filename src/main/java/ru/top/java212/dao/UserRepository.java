package ru.top.java212.dao;

import ru.top.java212.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Integer> {
    User findByLogin(String login);
}
