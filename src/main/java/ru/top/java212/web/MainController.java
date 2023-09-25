package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("title", "Главная страница"); // передаем данные внутрь шаблона
        return "home";
    }

//    @GetMapping("/user")
//    public String greeting(Model model) {
//        model.addAttribute("title", "Главная страница"); // передаем данные внутрь шаблона
//        return "home";
//    }
//
//    @GetMapping("/admin")
//    public String greeting(Model model) {
//        model.addAttribute("title", "Главная страница"); // передаем данные внутрь шаблона
//        return "home";
//    }




}
