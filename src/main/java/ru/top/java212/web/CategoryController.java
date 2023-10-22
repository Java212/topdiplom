package ru.top.java212.web;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.service.ProductService;

@Controller
public class CategoryController {

    @Autowired
    ProductService productService;

    @GetMapping("/categories/{titleCategory}")
    public ModelAndView categoryView(@PathVariable("titleCategory") String titleCategory, Model model) {
        ModelAndView mv = new ModelAndView("categories");
        mv.addObject("products", productService.getProductsByCategory(titleCategory));
        return mv;
    }


}
