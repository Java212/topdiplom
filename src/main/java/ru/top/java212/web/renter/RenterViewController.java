package ru.top.java212.web.renter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Order;
import ru.top.java212.model.Person;
import ru.top.java212.model.User;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.PersonRepository;
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.service.orders.OrderService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/renter/renterView")
public class RenterViewController {

    private final PersonRepository personRepository;
    private final OrderService orderService;

    @Autowired
    RenterViewController(PersonRepository personRepository,OrderService orderService){
        this.personRepository = personRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView showRenterView() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        Person personUser = personRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("renter/renterView");
        mv.addObject("personName",personUser.getName());
        mv.addObject("orders", orderService.findCurrentByPerson(personUser));
        mv.addObject("stoppedOrders",orderService.findStoppedByPerson(personUser));
        return  mv;
    }
}
