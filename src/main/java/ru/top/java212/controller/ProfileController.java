package ru.top.java212.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.entity.Product;
import ru.top.java212.entity.Profile;
import ru.top.java212.entity.User;
import ru.top.java212.repository.OrderRepository;
import ru.top.java212.repository.ProductRepository;
import ru.top.java212.repository.ProfileRepository;
import ru.top.java212.service.OrderService;


@Controller
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

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
        Profile profile = profileRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("personal-account");
        mv.addObject("profile", profile);
        mv.addObject("orders", orderRepository.findByProfile(profile));

        return mv;
    }

    @GetMapping("/personal-account/tools")
    public ModelAndView showProduct() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        Profile profile = profileRepository.findByUser(user);
        ModelAndView mv = new ModelAndView("userProducts");
        mv.addObject("profile", profile);
        mv.addObject("products", productRepository.findAllByProfile(profile));
        mv.addObject("mapOrders", orderService.findByListProduct(productRepository.findAllByProfile(profile)));
        return mv;
    }

    @PostMapping("/personal-account")
    public String userPost() {
        return "personal-account";
    }
}
