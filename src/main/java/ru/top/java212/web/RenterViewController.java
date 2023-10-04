package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/renter/renterView")
public class RenterViewController {

    private final PersonRepository personRepository;
    private final ToolRepository toolRepository;

    @Autowired
    RenterViewController(PersonRepository personRepository,ToolRepository toolRepository){
        this.personRepository = personRepository;
        this.toolRepository = toolRepository;
    }

    @GetMapping
    public ModelAndView showRenterView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/renterView");
        mv.addObject("personName",personUser.getName());
        mv.addObject("tools", toolRepository.findAll());
        return  mv;
    }

    @GetMapping("{toolId}")
    public ModelAndView tool(@PathVariable int toolId){
          ModelAndView mv = new ModelAndView("renter/toolCard");
          mv.addObject("tool",toolRepository.getReferenceById(toolId));
        return mv;
    }
}
