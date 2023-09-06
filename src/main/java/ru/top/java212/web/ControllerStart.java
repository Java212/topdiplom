package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerStart {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/menu")
    public ModelAndView menuDisplay(){
        ModelAndView mv = new ModelAndView("menu");
        int balanceFamily = 0;
        mv.addObject("balanceFamily", balanceFamily);
        return mv;
    }
}
