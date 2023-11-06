package ru.top.java212.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "API для взаимодействия с доходами")
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

    @Operation(summary = "Получить список доходов")
    @GetMapping("/list")
    public List<IncomeDTO> getIncomesDtoList() {
        return incomeService.getDtoList();
    }

    @Operation(summary = "Получить сумму всех доходов")
    @GetMapping("/summ_all")
    public Double getAllIncomesSumm(){
        return incomeService.getAllIncomesSumm();
    }

    @Operation(summary = "Получить сумму доходов пользователя по ID")
    @GetMapping("/summ/{id}")
    public Double getAllIncomesSummByUserId(@PathVariable Integer id){
        return incomeService.getAllIncomesSummByUserId(id);
    }

    @Operation(summary = "Добавить позицию доходов")
    @PostMapping
    public ModelAndView addIncome(@RequestBody IncomeDTO dto) {
        incomeService.convertAndSave(dto);
        return getIncomes();
    }

    @Operation(summary = "Удалить позицию расходов")
    @DeleteMapping("/{id}")
    public ModelAndView deleteIncome(@PathVariable Integer id){
        incomeService.deleteById(id);
        return getIncomes();
    }
}
