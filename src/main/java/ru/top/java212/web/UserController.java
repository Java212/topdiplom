package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.UserRepository;




@Controller
@RequestMapping("/user")
public class UserController {


    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("message", "Поздравляем, вы зарегистрировались");
        return modelAndView;
    }


}

