package ru.top.java212.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/toolCreatedView")
public class ToolCreatedView {

    @GetMapping
    public String showToolCreatedView(){
        return "toolView";
    }

}
