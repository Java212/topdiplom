package ru.top.java212.family;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class CalculationAllExpensesFamily implements AllExpensesFamily  {

        private final EntityManager entityManager;

        public CalculationAllExpensesFamily(EntityManager entityManager) {
                this.entityManager = entityManager;
        }

        @Override
        public int calculationExpensesFamily(LocalDate initalDate, LocalDate endData){
        TypedQuery<Integer> queryExp = entityManager.createNamedQuery("selectAllExpensesFamily", Integer.class);
        queryExp.setParameter("startData", initalDate);
        queryExp.setParameter("endData", endData);
        List<Integer> listExpenses = queryExp.getResultList();
        return listExpenses.stream().mapToInt(Integer::intValue).sum();
}

}
