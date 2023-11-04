package ru.top.java212.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.UserDto;
import ru.top.java212.service.ProductService;


@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @ModelAttribute("userDto")
    public UserDto userDto() {
        return new UserDto();
    }
    @GetMapping("/")
    public ModelAndView getProducts(@RequestParam(name = "error", required = false) String error) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("products", productService.getLastProductsByIsBusy(false));
        mv.addObject("loginError", error);
        return mv;
    }
}
