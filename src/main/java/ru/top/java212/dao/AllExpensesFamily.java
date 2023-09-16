package ru.top.java212.dao;

import java.time.LocalDate;
import java.util.Map;

public interface AllExpensesFamily {
    int calculationExpensesFamily(LocalDate initialDate, LocalDate endData);

    Map<String, Long> calculationExpensesFamilyByCategory(LocalDate initialDate, LocalDate endData);
}
