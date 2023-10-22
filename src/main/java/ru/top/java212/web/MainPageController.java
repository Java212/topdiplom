package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.UserDto;
import ru.top.java212.service.ProductService;


@Controller
public class MainPageController {


    @Autowired
    ProductService productService;
    @ModelAttribute("userDto")
    public UserDto userDto() {
        return new UserDto();
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)

    public ModelAndView getProducts() {

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("products", productService.getLastProductsByIsBusy(false));
        return mv;
    }



}
