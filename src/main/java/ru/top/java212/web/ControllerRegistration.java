package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.UserDbDao;
import ru.top.java212.model.Role;
import ru.top.java212.model.User;

import java.math.BigDecimal;

@Controller
public class ControllerRegistration {
    @Autowired
    UserDbDao userDao;

    @GetMapping("/registration")
    public String viewRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView addUserDataBase(@RequestParam ("userName") String userName,
                                      @RequestParam ("userLogin") String userLogin,
                                      @RequestParam ("userPassword") String userPassword,
                                      @RequestParam ("startCapitalUser") BigDecimal startCapitalUser){
        User user = new User(userName, userPassword, Role.USER, startCapitalUser);
        userDao.save(user);
        ModelAndView mv = new ModelAndView("registration");
        mv.addObject("message", "Регистрация прошла успешно!");
        return mv;
    }
}
