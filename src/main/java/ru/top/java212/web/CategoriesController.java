package ru.top.java212.web;

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

    @GetMapping("/income")
    public List<IncomeCategoryDTO> getIncomeCategories() {
        return incomeCategoryService.getIncomeCategoryDtoList();
    }

    @GetMapping("/expence")
    public List<ExpenceCategoryDTO> getExpenceCategories() {
        return expenceCategoryService.getExpenceCategoryDtoList();
    }

    @GetMapping("/income/used")
    public List<IncomeCategoryDTO> getUsedIncomeCategoryDtoList() {
        return incomeCategoryService.getUsedCategoryDtoList();
    }

    @GetMapping("/expence/used")
    public List<ExpenceCategoryDTO> getUsedExpenceCategoryDtoList() {
        return expenceCategoryService.getUsedCategoryDtoList();
    }

    @PostMapping("/income")
    public void addIncomeCategory(@RequestBody IncomeCategoryDTO dto) {
        incomeCategoryService.convertAndSave(dto);
    }

    @PostMapping("/expence")
    public void addExpenceCategory(@RequestBody ExpenceCategoryDTO dto) {
        expenceCategoryService.convertAndSave(dto);
    }

    @DeleteMapping("/income/{id}")
    public void deleteIncomeCategory(@PathVariable Integer id) {
        incomeCategoryService.deleteById(id);
    }

    @DeleteMapping("/expence/{id}")
    public void deleteExpenceCategory(@PathVariable Integer id) {
        expenceCategoryService.deleteById(id);
    }

}
