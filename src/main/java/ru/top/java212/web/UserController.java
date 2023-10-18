package ru.top.java212.web;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.dto.UserDto;
import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;
import ru.top.java212.service.UserService;


@Controller

public class UserController {
    private final UserService userService;
    private final UserInfoRepository userInfoRepository;

    public UserController(@Autowired UserService userService, UserInfoRepository userInfoRepository) {
        this.userService = userService;

        this.userInfoRepository = userInfoRepository;
    }


    @PostMapping("/registration")
    public String createUser(Model model, @ModelAttribute UserDto userDto) {
        userService.save(userDto);
        return "redirect:?authModal";
    }

    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Integer id, ch.qos.logback.core.model.Model model) {
        userInfoRepository.deleteById(id);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }

    @PostMapping("/update-user")
    public String updateUserInfo(Model model, @ModelAttribute UserInfo userInfo) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        userInfo.setUser(user);
        userService.updateUserInfo(userInfo);
        return "redirect:/personal-account";
    }
}

