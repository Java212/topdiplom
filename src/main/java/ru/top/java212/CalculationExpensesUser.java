package ru.top.java212;

import java.time.LocalDate;

public interface CalculationExpensesUser {

    int getExpenseUserForPeriod(int userId, LocalDate initialPeriod, LocalDate endPeriod);

    int getExpenseCategoryUser(int userId, String nameExpenseCategory);

    int getOverallAverageExpenseUser(int userId, LocalDate initialPeriod, LocalDate endPeriod);
}
