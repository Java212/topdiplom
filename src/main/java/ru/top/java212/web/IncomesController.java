package ru.top.java212.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.IncomeDTO;
import ru.top.java212.service.IncomeCategoryService;
import ru.top.java212.service.IncomeService;
import ru.top.java212.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/incomes")
@RequiredArgsConstructor
public class IncomesController {

    private final IncomeService incomeService;

    private final IncomeCategoryService incomeCategoryService;

    private final UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIncomes() {
        ModelAndView mv = new ModelAndView("incomes");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        mv.addObject("currentUser", userService.getUserDtoByName(currentPrincipalName));
        mv.addObject("categories", incomeCategoryService.getIncomeCategoryDtoList());
        return mv;
    }

    @GetMapping("/list")
    public List<IncomeDTO> getIncomesDtoList() {
        return incomeService.getDtoList();
    }

    @GetMapping("/summ_all")
    public Integer getAllIncomesSumm(){
        return incomeService.getAllIncomesSumm();
    }

    @PostMapping
    public ModelAndView addIncome(@RequestBody IncomeDTO dto) {
        incomeService.convertAndSave(dto);
        return getIncomes();
    }

    @DeleteMapping("/{id}")
    public ModelAndView deleteIncome(@PathVariable Integer id){
        incomeService.deleteById(id);
        return getIncomes();
    }
}
