package ru.top.java212.dao;

import java.time.LocalDate;
import java.util.Map;

public interface AllIncomesFamily {

    int calculationIncomesFamily(LocalDate initalDate, LocalDate endData);

    Map<String, Long> getIncomesFamilyBySource(LocalDate initalDate, LocalDate endData);
}
