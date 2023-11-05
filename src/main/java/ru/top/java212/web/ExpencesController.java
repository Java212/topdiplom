package ru.top.java212.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.ExpenceDTO;
import ru.top.java212.service.ExpenceCategoryService;
import ru.top.java212.service.ExpenceService;
import ru.top.java212.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/expences")
@RequiredArgsConstructor
public class ExpencesController {

    private final ExpenceService expenceService;

    private final ExpenceCategoryService expenceCategoryService;

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getExpences() {
        ModelAndView mv = new ModelAndView("expences");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        mv.addObject("currentUser", userService.getUserDtoByName(currentPrincipalName));
        mv.addObject("categories", expenceCategoryService.getExpenceCategoryDtoList());
        return mv;
    }

    @GetMapping("/list")
    public List<ExpenceDTO> getExpencesDtoList() {
        return expenceService.getDtoList();
    }

    @GetMapping("/summ_all")
    public Double getAllExpencesSumm(){
        return expenceService.getAllExpencesSumm();
    }

    @GetMapping("/summ/{id}")
    public Double getAllExpencesSummByUserId(@PathVariable Integer id){
        return expenceService.getAllExpencesSummByUserId(id);
    }

    @PostMapping
    public ModelAndView addExpence(@RequestBody ExpenceDTO dto) {
        expenceService.convertAndSave(dto);
        return getExpences();
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteExpence(@PathVariable Integer id){
        expenceService.deleteById(id);
        return getExpences();
    }
}
