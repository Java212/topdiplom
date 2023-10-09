package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {
    @RequestMapping(path = "/order", method = RequestMethod.GET)
    public String sale(){return "order";}
}
