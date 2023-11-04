package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.RegisterDTO;
import ru.top.java212.service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @PostMapping
    public void registerUser(@RequestBody RegisterDTO registerDTO) {
        userService.saveUser(registerDTO);
    }

    @GetMapping("/exist/{username}")
    public boolean checkDoesUserExist(@PathVariable String username){
        return userService.checkDoesUserExist(username);
    }
}
