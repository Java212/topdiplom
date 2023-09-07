package ru.top.java212;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.top.java212.model.User;

public interface UserDbDao extends JpaRepository<User, Integer> {

 public User findByName(String name);

}
