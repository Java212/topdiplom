package ru.top.java212.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.top.java212.dto.ExpenceCategoryDTO;
import ru.top.java212.dto.IncomeCategoryDTO;
import ru.top.java212.service.ExpenceCategoryService;
import ru.top.java212.service.IncomeCategoryService;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "wd")
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
    public void getIncomeCategories() {
        incomeCategoryService.getIncomeCategoryDtoList();
    }

    @GetMapping("/expence")
    public void getExpenceCategories() {
        expenceCategoryService.getExpenceCategoryDtoList();
    }

    @PostMapping("/income")
    public ModelAndView addIncomeCategory(@RequestBody IncomeCategoryDTO dto) {
        incomeCategoryService.convertAndSave(dto);
        return viewCategories();
    }

    @PostMapping("/expence")
    public ModelAndView addExpenceCategory(@RequestBody ExpenceCategoryDTO dto) {
        expenceCategoryService.convertAndSave(dto);
        return viewCategories();
    }

    @DeleteMapping("/income/{id}")
    public ModelAndView deleteIncomeCategory(@PathVariable Integer id) {
        incomeCategoryService.deleteById(id);
        return viewCategories();
    }

    @DeleteMapping("/expence/{id}")
    public ModelAndView deleteExpenceCategory(@PathVariable Integer id) {
        expenceCategoryService.deleteById(id);
        return viewCategories();
    }

}
