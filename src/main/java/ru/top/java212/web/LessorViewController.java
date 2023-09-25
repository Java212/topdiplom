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
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.service.tools.ToolService;


@Controller
@RequestMapping("/lessorView")
public class LessorViewController {


    private final PersonRepository personRepository;
    private final ToolService toolService;

    @Autowired
    LessorViewController(PersonRepository personRepository,ToolService toolService){
        this.personRepository = personRepository;
        this.toolService = toolService;
    }
    @GetMapping
    public ModelAndView showLessorView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("lessorView");
        mv.addObject("personName",personUser.getName());
        mv.addObject("tools",toolService.findAllByUser(user));
        return  mv;
    }
}
