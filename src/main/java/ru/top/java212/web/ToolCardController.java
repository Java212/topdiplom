package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.OrderDTO;
import ru.top.java212.model.Person;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.service.orders.OrderService;

import java.time.LocalDate;

@Controller
@RequestMapping("/renter/toolCard")
public class ToolCardController {



    @Autowired
    private  ToolRepository toolRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private OrderService orderService;


    @GetMapping
    public ModelAndView showToolCardView(@RequestParam(value = "toolId") int toolId){
        ModelAndView mv = new ModelAndView("/renter/toolCard");
        Tool tool = toolRepository.getReferenceById(toolId);
        mv.addObject("order",new OrderDTO());
        mv.addObject("tool",tool);
        return mv;
    }
    @PostMapping
    public ModelAndView createdOrder(@ModelAttribute("order") OrderDTO order){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        Tool tool = toolRepository.getReferenceById(order.getToolId());
        orderService.save(personUser,tool,order.getStartDate(),order.getStopDate());
        ModelAndView mv = new ModelAndView("renter/renterView");
        return mv;
    }

}
