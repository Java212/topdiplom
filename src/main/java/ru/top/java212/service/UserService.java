package ru.top.java212.service;

import ru.top.java212.model.User;

public interface UserService {
    void save(User user);

    User findByUserName(String userName);
}
