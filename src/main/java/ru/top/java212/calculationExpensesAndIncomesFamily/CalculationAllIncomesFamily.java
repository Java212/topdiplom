package ru.top.java212.calculationExpensesAndIncomesFamily;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllIncomesFamily;
import ru.top.java212.dao.IncomeAmount;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.dao.TotalIncome;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationAllIncomesFamily implements AllIncomesFamily {
    private final IncomeDbDao incomeDao;

    @Autowired
    public CalculationAllIncomesFamily(IncomeDbDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    @Override
    public int calculationIncomesFamily(LocalDate startDate, LocalDate endData) {
        List<IncomeAmount> list = incomeDao.findByDateBetween(startDate, endData);
        return list.stream().mapToInt(IncomeAmount::getIncomeAmount).reduce(0, Integer::sum);
    }

    @Override
    public Map<String, Long> calculationSourceIncomeByCategory(LocalDate startDate, LocalDate endData) {
        List<TotalIncome> list = incomeDao.getIncomesFamilyByCategory(startDate, endData);
        Map<String, Long> map = new HashMap<>();
        for (TotalIncome i : list) {
            map.put(i.getCategoryName(), i.getTotal());
        }
        return map;
    }
}
