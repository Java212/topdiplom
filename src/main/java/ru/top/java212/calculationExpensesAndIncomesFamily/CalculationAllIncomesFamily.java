package ru.top.java212.calculationExpensesAndIncomesFamily;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllIncomesFamily;

import java.time.LocalDate;
import java.util.List;

@Component
public class CalculationAllIncomesFamily implements AllIncomesFamily {

        private final EntityManager entityManager;

        public CalculationAllIncomesFamily(EntityManager entityManager) {
                this.entityManager = entityManager;
        }

        @Override
        public int calculationIncomesFamily(LocalDate initalDate, LocalDate endData){
        TypedQuery<Integer> queryInc = entityManager.createNamedQuery("selectAllIncomesFamily", Integer.class);
        queryInc.setParameter("startData", initalDate);
        queryInc.setParameter("endData", endData);
        List<Integer> listExpenses = queryInc.getResultList();
        return listExpenses.stream().mapToInt(Integer::intValue).sum();
}
        //todo сделать метод расчета доходов семьи по источникам
}
