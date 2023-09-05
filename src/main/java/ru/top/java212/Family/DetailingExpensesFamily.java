package ru.top.java212.Family;

import java.time.LocalDate;
import java.util.Map;

public interface DetailingExpensesFamily {

    Map<String, Integer> detailingExpensesFamily(LocalDate initialPeriod, LocalDate endPeriod);
}
