package ru.top.java212.family;

import java.time.LocalDate;

public interface FamilyBalance {

    int getBalance(LocalDate initialPeriod, LocalDate endPeriod);

}
