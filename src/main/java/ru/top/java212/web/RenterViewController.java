package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/renterView")
public class RenterViewController {

    @GetMapping
    public String showRegistrationForm() {
        return "renterView";
    }
}
