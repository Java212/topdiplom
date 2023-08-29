package ru.top.java212;

import java.time.LocalDate;

public interface CalculationIncomesUser {

    int getIncomeUserForPeriod(int userId, LocalDate initialPeriod, LocalDate endPeriod);

      int getOverallAverageIncomeUser(int userId, LocalDate initialPeriod, LocalDate endPeriod);
}
