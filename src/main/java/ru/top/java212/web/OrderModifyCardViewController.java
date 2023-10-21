package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Order;
import ru.top.java212.service.orders.OrderService;

@Controller
@RequestMapping("/orderModifyCardView")
public class OrderModifyCardViewController {

    private OrderService orderService;

    @Autowired
    OrderModifyCardViewController(OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView showOrderModifyCardView(@RequestParam(value = "orderId") int orderId){
        Order order = orderService.findOrderById(orderId);
        ModelAndView mv = new ModelAndView("/orderModifyCardView");
        mv.addObject("tool",order.getTool());
        mv.addObject("order",order);
        return mv;
    }

    @PostMapping
    public ModelAndView stopOrder(){
        ModelAndView mv = new ModelAndView();
        return mv;
    }

}
