package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;

@Controller
public class ViewController {
    @Autowired
    PersonRepository personRepository;
    @GetMapping("/")
    public ModelAndView indexGet(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("/index");
        mv.addObject("personName", personUser.getName());
        return mv;
    }
    @PostMapping("/")
    public ModelAndView  indexPost(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("/index");
        mv.addObject("personName", personUser.getName());
        return mv;
    }
    @RequestMapping({"/login"})
    public String login() {
        return "login";
    }

}
