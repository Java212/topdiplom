package ru.top.java212.web.lessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.service.tools.ToolService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/lessor/lessorView")
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
        User user = (principal instanceof User) ? ((User) principal) : new User();
        Person personUser = personRepository.findByUser(user);
        List<Tool> userTools = toolService.findAllByUser(user);
        List<Order> userOrders = toolService.getOrdersByTools(userTools);
        ModelAndView mv = new ModelAndView("lessor/lessorView");
        mv.addObject("personName", personUser.getName());
        mv.addObject("tools", userTools);
        mv.addObject("orders",userOrders);
        return mv;
    }

}
