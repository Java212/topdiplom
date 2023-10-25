package ru.top.java212.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationAllExpensesFamily;
import ru.top.java212.calculationExpensesAndIncomesFamily.CalculationAllIncomesFamily;
import ru.top.java212.model.Expense;
import ru.top.java212.model.ExpenseCategory;
import ru.top.java212.model.Income;
import ru.top.java212.model.IncomeCategory;

import java.time.LocalDate;
import java.util.Map;

@SpringBootTest
public class RedactRecordsInDbTest {
    @Autowired
    RedactRecordsInDb redactRecordsInDb;
    @Autowired
    CalculationAllExpensesFamily calculationAllExpensesFamily;

    @Autowired
    CalculationAllIncomesFamily calculationAllIncomesFamily;

    @Test
    @Disabled
    void test_method_editingCategoryNamesExpense() {
        String currentName = "расходы на питание";
        String newName = "продукты питания";

        redactRecordsInDb.editingCategoryNamesExpense(currentName, newName);
        ExpenseCategory result = redactRecordsInDb.getExpenseCategoryList().stream()
                .filter(e -> e.getNameExpenseCategory().equals(newName)).findAny().orElseThrow();
        Assertions.assertEquals(newName, result.getNameExpenseCategory());

        int sumAllExpenses = result.getExpenses().stream().mapToInt(Expense::getExpenseAmount).sum();
        Assertions.assertEquals(17000, sumAllExpenses);
    }

    @Test
    @Disabled
    void test_method_editingCategoryNamesIncome() {
        String currentName = "доходы от ценных бумаг";
        String newName = "дивиденды";

        redactRecordsInDb.editingCategoryNamesIncome(currentName, newName);
        IncomeCategory result = redactRecordsInDb.getIncomeSourceList().stream()
                .filter(c -> c.getSourceIncomeCategory().equals(newName))
                .findAny().orElseThrow();

        Assertions.assertEquals(newName, result.getSourceIncomeCategory());

        int sumAllExpenses = result.getIncomes().stream().mapToInt(Income::getIncomeAmount).sum();
        Assertions.assertEquals(10000, sumAllExpenses);
    }

    @Test
    @Disabled
    void test_remove_Category_from_Db_for_expense_category() {

        String nameRemoveCategory = "покупки непродовольственных товаров";
        redactRecordsInDb.removeExpenseCategory(nameRemoveCategory);

        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 10, 31);
        Map<String, Long> list = Map.of("коммунальные платежи", 31140L,
                "расходы на питание", 30140L,
                "транспортные расходы", 17640L,
                "расходы на мобильную связь и интернет", 14840L,
                "покупка лекарственных средств", 26140L,
                "непредвиденные расходы", 43285L);
        Assertions.assertEquals(list, calculationAllExpensesFamily.calculationExpensesFamilyByCategory(startPeriod, endPeriod));
    }

    @Test
    @Disabled
    void test_remove_Category_from_Db_for_income_source() {

        String nameRemoveCategory = "премия";
        redactRecordsInDb.removeIncomeSource(nameRemoveCategory);

        LocalDate startPeriod = LocalDate.of(2023, 9, 1);
        LocalDate endPeriod = LocalDate.of(2023, 10, 31);
        Map<String, Long> list = Map.of("заработная плата", 251250L,
                "доходы от ценных бумаг", 43500L,
                "стипендия", 26750L,
                "доходы от предпренимательской деятельности", 111500L,
                "доходы от других источников", 33750L);
        Assertions.assertEquals(list, calculationAllIncomesFamily.calculationSourceIncomeByCategory(startPeriod, endPeriod));
    }
}
