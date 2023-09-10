package ru.top.java212.calculationExpensesAndIncomesFamily;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllExpensesFamily;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalculationAllExpensesFamily implements AllExpensesFamily {

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

        @Override
        public Map<String, Long> getExpensesByCategory(LocalDate initalDate, LocalDate endData){
                TypedQuery<Tuple> query = entityManager.createQuery("""
                        select expenseCategory.nameExpenseCategory as category, sum(expenseAmount) as total
                        from Expense where date between : startData and : endData
                        group by expenseCategory.nameExpenseCategory""", Tuple.class);
                query.setParameter("startData", initalDate);
                query.setParameter("endData", endData);
                return query.getResultList()
                        .stream()
                        .collect(
                                Collectors.toMap(
                                        tuple -> ((String) tuple.get("category")),
                                        tuple -> ((Number) tuple.get("total")).longValue()
                                )
                        );
        }

}
