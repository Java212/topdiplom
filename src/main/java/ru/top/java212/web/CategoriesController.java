package ru.top.java212.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.ExpenceCategoryDTO;
import ru.top.java212.dto.IncomeCategoryDTO;
import ru.top.java212.service.ExpenceCategoryService;
import ru.top.java212.service.IncomeCategoryService;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "API для взаимодействия с категориями")
public class CategoriesController {

    private final IncomeCategoryService incomeCategoryService;

    private final ExpenceCategoryService expenceCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView viewCategories(){
        ModelAndView mv = new ModelAndView("categories");
        mv.addObject("incomeCategories",
                incomeCategoryService.getIncomeCategoryDtoList());
        mv.addObject("expenceCategories",
                expenceCategoryService.getExpenceCategoryDtoList());
        return mv;
    }

    @Operation(summary = "Получить список катогорий доходов")
    @GetMapping("/income")
    public List<IncomeCategoryDTO> getIncomeCategories() {
        return incomeCategoryService.getIncomeCategoryDtoList();
    }

    @Operation(summary = "Получить список категорий доходов")
    @GetMapping("/expence")
    public List<ExpenceCategoryDTO> getExpenceCategories() {
        return expenceCategoryService.getExpenceCategoryDtoList();
    }

    @Operation(summary = "Получить список используемых категорий доходов")
    @GetMapping("/income/used")
    public List<IncomeCategoryDTO> getUsedIncomeCategoryDtoList() {
        return incomeCategoryService.getUsedCategoryDtoList();
    }

    @Operation(summary = "Получить список используемых категорий расходов")
    @GetMapping("/expence/used")
    public List<ExpenceCategoryDTO> getUsedExpenceCategoryDtoList() {
        return expenceCategoryService.getUsedCategoryDtoList();
    }

    @Operation(summary = "Добавить категорию доходов")
    @PostMapping("/income")
    public void addIncomeCategory(@RequestBody IncomeCategoryDTO dto) {
        incomeCategoryService.convertAndSave(dto);
    }

    @Operation(summary = "Добавить категорию расходов")
    @PostMapping("/expence")
    public void addExpenceCategory(@RequestBody ExpenceCategoryDTO dto) {
        expenceCategoryService.convertAndSave(dto);
    }

    @Operation(summary = "Удалить существующую категорию доходов")
    @DeleteMapping("/income/{id}")
    public void deleteIncomeCategory(@PathVariable Integer id) {
        incomeCategoryService.deleteById(id);
    }

    @Operation(summary = "Удалить существующую категорию расходов")
    @DeleteMapping("/expence/{id}")
    public void deleteExpenceCategory(@PathVariable Integer id) {
        expenceCategoryService.deleteById(id);
    }

}
