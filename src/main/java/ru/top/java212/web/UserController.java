package ru.top.java212.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import ru.top.java212.dto.UserDto;
import ru.top.java212.service.UserService;


@Controller

public class UserController {
    private final UserService userService;


    public UserController(@Autowired UserService userService) {
        this.userService = userService;

    }


    @PostMapping("/registration")
    public String createUser(Model model, @ModelAttribute UserDto userDto) {
        userService.save(userDto);
        return "redirect:?authModal";
    }
}

