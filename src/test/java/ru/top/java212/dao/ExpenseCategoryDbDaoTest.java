package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
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
    void test_save_and_set_is_same(){
        ExpenseCategory expCategoryFromDb = categoryDbDao.findById(1).orElseThrow();
        expCategoryFromDb.setNameExpenseCategory("аренда магазина");
        Assertions.assertEquals("аренда магазина", expCategoryFromDb.getNameExpenseCategory());
    }
    @Test
    @Transactional
    @Disabled
    void test_delete_Category(){
        String nameRemoveCategory = "квартплата";
        int colRemoveRow = categoryDbDao.deleteByNameExpenseCategory(nameRemoveCategory);
        Assertions.assertEquals(1, colRemoveRow);
    }
}
