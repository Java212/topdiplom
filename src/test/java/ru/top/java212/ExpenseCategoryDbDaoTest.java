package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.model.ExpenseCategory;

@SpringBootTest
public class ExpenseCategoryDbDaoTest {

    @Autowired
    ExpenseCategoryDbDao categoryDbDao;

    @Test
    void test_categories_are_mapped(){

        Assertions.assertDoesNotThrow(() -> categoryDbDao.findAll());
    }
    @Test
    void test_save_and_get_is_same(){
        ExpenseCategory expCategory = new ExpenseCategory("продукты питания", 56000);
        categoryDbDao.save(expCategory);
        ExpenseCategory expCategoryFromDb = categoryDbDao.findById(2).orElseThrow();
        Assertions.assertEquals(expCategory.getNameExpenseCategory(),expCategoryFromDb.getNameExpenseCategory());
        Assertions.assertEquals(expCategory.getAmountExpenseCategory(),expCategoryFromDb.getAmountExpenseCategory());
    }

    @Test
    void test_save_and_set_is_same(){
        ExpenseCategory expCategoryFromDb = categoryDbDao.findById(1).orElseThrow();
        expCategoryFromDb.setNameExpenseCategory("аренда магазина");
        Assertions.assertEquals("аренда магазина", expCategoryFromDb.getNameExpenseCategory());
    }
}
