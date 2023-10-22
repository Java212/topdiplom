package ru.top.java212.web;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.model.Product;
import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;
import ru.top.java212.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;



    @PostMapping("/create-product")
    public String createProduct(Model model, @ModelAttribute Product product) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        UserInfo userInfo = userInfoRepository.findByUser(user);
        product.setUserInfo(userInfo);
        productService.saveProduct(product);
        return "redirect:personal-account/tools";
    }

    @GetMapping("/delete/product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        productService.delete(id);
        return "redirect:/personal-account/tools";
    }

    @GetMapping("/find-product")
    public ModelAndView findProductByTitle(Model model, @Param("title") String title ) {
        ModelAndView mv = new ModelAndView("categories");
        mv.addObject("products", productService.getProductsByTitleNotBusy(false, title));
        return mv;
    }
}

