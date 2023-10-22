package ru.top.java212.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.OrderRepository;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.model.Product;
import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;
import ru.top.java212.service.OrderService;


@Controller
public class PersonalAccountController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;


    @ModelAttribute("product")
    public Product product() {
        return new Product();
    }

    @GetMapping("/personal-account")
    public ModelAndView showPersonalAccount() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        UserInfo userInfo = userInfoRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("personal-account");
        mv.addObject("userInfo", userInfo);
        mv.addObject("orders", orderRepository.findByUserInfo(userInfo));

        return mv;
    }

    @GetMapping("/personal-account/tools")
    public ModelAndView showMyTools() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        UserInfo userInfo = userInfoRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("myTools");
        mv.addObject("userInfo", userInfo);
        mv.addObject("products", productRepository.findAllByUserInfo(userInfo));
        mv.addObject("mapOrders", orderService.findByListProduct(productRepository.findAllByUserInfo(userInfo)));
        return mv;
    }

    @PostMapping("/personal-account")
    public String personPost() {
        return "personal-account";
    }


}
