package ru.top.java212.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.model.IncomeCategory;

@SpringBootTest
public class IncomeCategoryDbDaoTest {

    @Autowired
    IncomeCategoryDbDao categoryDbDao;

    @Test
    void test_categories_are_mapped(){

        Assertions.assertDoesNotThrow(() -> categoryDbDao.findAll());
    }

    @Test
    @Disabled
    void test_save_and_get_is_same(){
        IncomeCategory inCategory = new IncomeCategory("дивиденды");
        categoryDbDao.save(inCategory);
        IncomeCategory inCategoryFromDb = categoryDbDao.findById(7).orElseThrow();
        Assertions.assertEquals(inCategory.getSourceIncomeCategory(),inCategoryFromDb.getSourceIncomeCategory());
    }
    @Test
    @Disabled
    void test_save_and_set_is_same(){
        IncomeCategory inCategoryFromDb = categoryDbDao.findById(1).orElseThrow();
        inCategoryFromDb.setSourceIncomeCategory("премия");
        Assertions.assertEquals("премия", inCategoryFromDb.getSourceIncomeCategory());
    }
    @Test
    void test_method_findBySourceIncomeCategory(){
        String searchNameInTheDb = "доходы от ценных бумаг";
        String resultFromDb = categoryDbDao.findBySourceIncomeCategory(searchNameInTheDb).getSourceIncomeCategory();
        Assertions.assertEquals(searchNameInTheDb, resultFromDb);
    }
    @Test
    @Disabled
    @Transactional
    void test_method_delete_source_income(){
        String nameRemoveCategory = "премия";
        int colRemoveRow = categoryDbDao.deleteBySourceIncomeCategory(nameRemoveCategory);
        Assertions.assertEquals(1, colRemoveRow);
    }
}
