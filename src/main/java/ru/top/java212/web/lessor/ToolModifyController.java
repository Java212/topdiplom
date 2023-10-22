package ru.top.java212.web.lessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.model.Tool;
import ru.top.java212.service.tools.ToolService;

@Controller
@RequestMapping("/lessor/toolModifyCardView")
public class ToolModifyController {
    private final ToolService toolService;

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
        try{
            toolService.deleteById(toolId);
            ModelAndView mv = new ModelAndView("redirect:/lessor/lessorView");
            return mv;
        } catch (RuntimeException e){
            ModelAndView mv = new ModelAndView("/lessor/toolModifyCardView");
            Tool tool = toolService.getToolById(toolId);
            mv.addObject("tool",tool);
            mv.addObject("message",true);
            return mv;
        }
    }
}
