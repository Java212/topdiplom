package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dto.UserDto;


@Controller
public class MainPageController {


    @Autowired
   ProductRepository productRepository;
    @ModelAttribute("userDto")
    public UserDto userDto() {
        return new UserDto();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)

    public ModelAndView getProducts() {

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("products", productRepository.findByIsBusy(false));
        return mv;
    }


}
