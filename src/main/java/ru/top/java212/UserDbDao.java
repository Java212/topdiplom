package ru.top.java212;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.User;

public interface UserDbDao extends CrudRepository<User, Integer> {


}
