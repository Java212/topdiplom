package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ProductRepository;

@Controller
public class UshmController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(path = "/ushm", method = RequestMethod.GET)

    public ModelAndView getProducts() {

        ModelAndView mv = new ModelAndView("ushm");
        mv.addObject("products", productRepository.getProductsByCategory(1));
        return mv;
    }

}
