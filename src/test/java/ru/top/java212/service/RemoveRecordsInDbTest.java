package ru.top.java212.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class RemoveRecordsInDbTest {
    @Autowired
    RemoveRecordsInDb removeRecordsInDb;

    @Test
    @Disabled
    void test_remove_Category_from_Db_for_expenseCategory(){
        String whatRemove = "расходы";
        String nameRemoveCategory = "непредвиденные расходы";
        int colRemoveRow = removeRecordsInDb.removeCategory(whatRemove, nameRemoveCategory);
        Assertions.assertEquals(1, colRemoveRow);
    }
    @Test
    @Disabled
    void test_remove_Category_from_Db_for_incomeSource(){
        String whatRemove = "доходы";
        String nameRemoveCategory = "доходы от других источников";
        int colRemoveRow = removeRecordsInDb.removeCategory(whatRemove, nameRemoveCategory);
        Assertions.assertEquals(1, colRemoveRow);
    }
}
