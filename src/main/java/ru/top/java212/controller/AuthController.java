package ru.top.java212.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.top.java212.dto.UserDto;
import ru.top.java212.entity.Profile;
import ru.top.java212.entity.User;
import ru.top.java212.repository.ProfileRepository;
import ru.top.java212.service.UserServiceImpl;

@Controller
public class AuthController {
    private final UserServiceImpl userService;
    private final ProfileRepository profileRepository;
    public AuthController(@Autowired UserServiceImpl userService, ProfileRepository profileRepository) {
        this.userService = userService;

        this.profileRepository = profileRepository;
    }
    @PostMapping("/registration")
    public String createUser(Model model, @ModelAttribute UserDto userDto) {
        userService.save(userDto);
        return "redirect:?authModal";
    }
    @GetMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        profileRepository.deleteById(id);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/";
    }
}
