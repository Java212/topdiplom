package ru.top.java212.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.top.java212.dto.UserRegistrationDTO;
import ru.top.java212.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    Boolean save(UserRegistrationDTO registrationDto);

    List<User> allUsers();
}
