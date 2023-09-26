package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ProductRepository;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(path = "/batteryTool", method = RequestMethod.GET)
    public ModelAndView getBatteryTool() {

        ModelAndView mv = new ModelAndView("batteryTool");
        mv.addObject("products", productRepository.getProductsByCategory(3));
        return mv;
    }

    @RequestMapping(path = "/punchers", method = RequestMethod.GET)
    public ModelAndView getPunchers() {

        ModelAndView mv = new ModelAndView("punchers");
        mv.addObject("products", productRepository.getProductsByCategory(2));
        return mv;
    }

    @RequestMapping(path = "/uhm", method = RequestMethod.GET)
    public ModelAndView getUhm() {

        ModelAndView mv = new ModelAndView("uhm");
        mv.addObject("products", productRepository.getProductsByCategory(1));
        return mv;
    }
}
