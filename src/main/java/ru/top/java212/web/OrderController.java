package ru.top.java212.web;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dao.ProductRepository;
import ru.top.java212.dao.UserInfoRepository;
import ru.top.java212.dto.OrderDto;

import ru.top.java212.model.User;
import ru.top.java212.model.UserInfo;
import ru.top.java212.service.OrderService;



@Controller
public class OrderController {

   private final OrderService orderService;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    public OrderController(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }
    @ModelAttribute("orderDto")
    public OrderDto orderDto() {
        return new OrderDto();
    }

    @GetMapping("/order/{id}")
    public ModelAndView orderView(@PathVariable("id") Integer id, Model model) {
        ModelAndView mv = new ModelAndView("order");
        mv.addObject("product", productRepository.findById(id).orElse(null));
        return mv;
    }

    @PostMapping("/save")
    public String createOrder(Model model, @ModelAttribute OrderDto orderDto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (principal instanceof User) ? ((User) principal) : new User();
        UserInfo userInfo = userInfoRepository.findByUser(user);
        orderDto.setUserInfo(userInfo);
        orderService.save(orderDto);
        return "redirect:personal-account";
    }

    @GetMapping("/order/delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id, Model model) {
       orderService.delete(id);
        return "redirect:/personal-account";
    }


}
