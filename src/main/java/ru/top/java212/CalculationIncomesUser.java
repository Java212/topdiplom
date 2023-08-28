package ru.top.java212;

import java.time.LocalDate;

public interface CalculationIncomesUser {

    int getIncomeUserForPeriod(int userId, LocalDate initialPeriod, LocalDate endPeriod);

    int getIncomeUserForMonth(int userId, String month);

    int getOverallAverageIncomeUser(int userId, LocalDate initialPeriod, LocalDate endPeriod);
}
