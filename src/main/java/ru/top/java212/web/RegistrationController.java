package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.UserDbDao;
import ru.top.java212.dto.UserDto;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;

@Controller
public class RegistrationController {
    @Autowired
    UserDbDao userDao;

    @GetMapping("/registration")
    public ModelAndView viewRegistration() {
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject("newUser", new UserDto("", "", "", new BigDecimal(0)));
        return mv;
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@ModelAttribute UserDto newUser) {
        User user = new User(newUser.name(), newUser.login(), newUser.password(), Role.USER, newUser.startCapital());
        userDao.save(user);
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject("message", "Регистрация прошла успешно!");
        return mv;
    }
}
