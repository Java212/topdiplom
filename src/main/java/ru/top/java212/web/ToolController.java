package ru.top.java212.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.top.java212.model.Tool;
import ru.top.java212.service.ToolService;


@RequestMapping("/toolView")
@Controller
public class ToolController {
    private final ToolService toolService;
    @Autowired
    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }
    @GetMapping("/toolV")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("tools", toolService.listTools(title));
        return "tools";
    }
    @GetMapping("/tool/{id}")
    public String toolInfo(@PathVariable Long id, Model model) {
        model.addAttribute("tool", toolService.getToolsById(id));
        return "tool-info";
    }
    @PostMapping("/tool/create")
    public String createTool(Tool tool) {
        toolService.saveTool(tool);
        return "redirect:/";
    }
    @PostMapping("/tool/{id}/delete")
    public String deleteTool(@PathVariable(name = "id") Long id) {
        toolService.deleteTool(id);
        return "redirect:/";
    }
}