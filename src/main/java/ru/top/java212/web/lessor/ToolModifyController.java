package ru.top.java212.web.lessor;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Tool;
import ru.top.java212.model.User;
import ru.top.java212.repository.ToolRepository;
import ru.top.java212.service.tools.ToolService;

@Controller

@RequestMapping("/lessor/toolModifyCardView")
public class ToolModifyController {
    ToolService toolService;

    @Autowired
    private ToolModifyController(ToolService toolService){
        this.toolService = toolService;
    }
    @GetMapping
    public ModelAndView showToolModifyView(@RequestParam(value = "toolId") int toolId){
        ModelAndView mv = new ModelAndView("lessor/toolModifyCardView");
        Tool tool = toolService.getToolById(toolId);
        mv.addObject("tool",tool);
        return mv;
    }

    @PostMapping
    public ModelAndView removeTool(@RequestParam(value = "toolId") int toolId){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = ( principal instanceof User)? ((User) principal):new User();
        toolService.deleteById(toolId);
        ModelAndView mv = new ModelAndView("lessor/lessorView");
        mv.addObject("tools", toolService.findAllByUser(user));
        return  mv;
    }
}
