package ru.top.java212.dao;

import java.time.LocalDate;
import java.util.Map;

public interface AllIncomesUser {

    int calculationIncomesUser(int userId, LocalDate initialPeriod, LocalDate endPeriod);

    Map<String, Long> getIncomesUserBySource(int userId, LocalDate initalDate, LocalDate endData);
}
