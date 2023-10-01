package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ViewController {
    @GetMapping("/")
    public String indexGet(){
        return "index";
    }
    @PostMapping("/")
    public String  indexPost(){
        return "index";
    }
    @RequestMapping({"/login"})
    public String login() {
        return "login";
    }

}
