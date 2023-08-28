package ru.top.java212;

import java.time.LocalDate;

public interface CalculationBudget {

    int getBudget(int userId, LocalDate initialPeriod, LocalDate endPeriod);
}
