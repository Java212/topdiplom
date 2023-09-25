package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.ToolDTO;
import ru.top.java212.model.User;
import ru.top.java212.service.tools.ToolService;

@Controller
@RequestMapping("/toolCreatedView")
public class ToolCreatedView {

    @Autowired
    ToolService toolService;

    @ModelAttribute("toolForm")
    private ToolDTO toolDTO(){
        return new ToolDTO();
    }

    @GetMapping
    public String showToolCreatedView(){
        return "toolCreatedView";
    }

    @PostMapping
    public String addTool(@ModelAttribute("toolForm") ToolDTO toolDTO){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        String price = toolDTO.getPrice();
        toolService.save(toolDTO,user);
       return  "lessorView";
    }

}
