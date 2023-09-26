package ru.top.java212.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.top.java212.dto.UserDto;
import ru.top.java212.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    boolean save(UserDto userDTO);
    boolean save(User user);
    List<UserDto> getAll();
    Optional<User> findById(Integer userId);

    User findByUserName(String name);
    void updateProfile(UserDto userDTO);
}
