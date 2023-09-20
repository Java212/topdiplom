package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.UserRepository;


@Controller
@RequestMapping("/renterView")
public class RenterViewController {

    private final PersonRepository personRepository;

    @Autowired
    RenterViewController(PersonRepository personRepository){

        this.personRepository = personRepository;
    }

    @GetMapping
    public ModelAndView showRenterView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renterView");
        mv.addObject("personName",personUser.getName());
        return  mv;
    }
}
