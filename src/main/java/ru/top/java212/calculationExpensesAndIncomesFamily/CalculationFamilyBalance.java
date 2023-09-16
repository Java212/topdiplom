package ru.top.java212.calculationExpensesAndIncomesFamily;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllExpensesFamily;
import ru.top.java212.dao.AllIncomesFamily;
import ru.top.java212.dao.FamilyBalance;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//todo после написания класса CalculationIncomesFamily переписать реализацию класса CalculationFamilyBalance
@Component
public class CalculationFamilyBalance implements FamilyBalance {
    private final EntityManager entityManager;

    @Autowired
    AllIncomesFamily incomesFamily;
    @Autowired
    AllExpensesFamily expensesFamily;
    @Autowired
    public CalculationFamilyBalance(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    //
//    @Override
//    public int getBalance(LocalDate initialPeriod, LocalDate endPeriod){
//        TypedQuery<BigDecimal> queryUserCapital = entityManager.createNamedQuery("selectAllUserStartingCapital", BigDecimal.class);
//        List<BigDecimal> listCapitalUser = queryUserCapital.getResultList();
//        int allCapitalUsers = listCapitalUser.stream().mapToInt(BigDecimal::intValue).sum();
//
//        TypedQuery<Integer> queryExp = entityManager.createNamedQuery("selectAllExpenses", Integer.class);
//        queryExp.setParameter("startData", initialPeriod);
//        queryExp.setParameter("endData", endPeriod);
//        List<Integer> listExpenses = queryExp.getResultList();
//        int allSummaExpenses = listExpenses.stream().mapToInt(Integer::intValue).sum();
//
//        TypedQuery<Integer> queryInc = entityManager.createNamedQuery("selectAllIncomes", Integer.class);
//        queryInc.setParameter("startData", initialPeriod);
//        queryInc.setParameter("endData", endPeriod);
//        List<Integer> listIncomes = queryInc.getResultList();
//        int allSummaIncomes = listIncomes.stream().mapToInt(Integer::intValue).sum();
//
//        return allCapitalUsers+allSummaIncomes-allSummaExpenses;
//    }
        @Override
        public int getBalance(LocalDate initialPeriod, LocalDate endPeriod){
            TypedQuery<BigDecimal> queryUserCapital = entityManager.createNamedQuery("selectAllUserStartingCapital", BigDecimal.class);
        List<BigDecimal> listCapitalUser = queryUserCapital.getResultList();
        int allCapitalUsers = listCapitalUser.stream().mapToInt(BigDecimal::intValue).sum();

        return allCapitalUsers+incomesFamily.calculationIncomesFamily(initialPeriod, endPeriod)-expensesFamily.calculationExpensesFamily(initialPeriod, endPeriod);
    }
}
