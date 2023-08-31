package ru.top.java212.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
