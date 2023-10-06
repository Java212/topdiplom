package ru.top.java212;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.top.java212.service.RemoveRecordsInDb;

@SpringBootTest
@Transactional
public class RemoveRecordsInDbTest {
    @Autowired
    RemoveRecordsInDb removeRecordsInDb;

    @Test
    @Disabled
    void test_remove_Category_from_Db(){
        String whatRemove = "расходы";
        String nameRemoveCategory = "квартплата";
        int colRemoveRow = removeRecordsInDb.removeCategory(whatRemove, nameRemoveCategory);
        Assertions.assertEquals(1, colRemoveRow);
    }
}
