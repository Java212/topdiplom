package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import ru.top.java212.dao.UserRepository;
import ru.top.java212.model.User;



@RestController
@RequestMapping("users")
public class UserController {
    private final UserRepository userRepository;



    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @PostMapping(path = "/registration")
    public User registerUser(@RequestBody User user) {
       return userRepository.save(user);

    }


}

