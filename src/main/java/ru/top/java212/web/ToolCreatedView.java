package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.service.tools.ToolService;

@Controller
@RequestMapping("/toolCreatedView")
public class ToolCreatedView {

    @Autowired
    ToolService toolService;
    @Autowired
    PersonRepository personRepository;

    @ModelAttribute("toolForm")
    private ToolDTO toolDTO(){
        return new ToolDTO();
    }

    @GetMapping
    public String showToolCreatedView(){
        return "toolCreatedView";
    }

    @PostMapping
    public ModelAndView addTool(@ModelAttribute("toolForm") ToolDTO toolDTO){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        toolService.save(toolDTO,user);
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("lessorView");
        mv.addObject("personName",personUser.getName());
        mv.addObject("tools",toolService.findAllByUser(user));
       return  mv;
    }

}
