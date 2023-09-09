package ru.top.java212.family;

import java.time.LocalDate;
import java.util.Map;

public interface AllExpensesFamily {
    int calculationExpensesFamily(LocalDate initalDate, LocalDate endData);

    Map<String, Long> getExpensesByCategory(LocalDate initalDate, LocalDate endData);
}
