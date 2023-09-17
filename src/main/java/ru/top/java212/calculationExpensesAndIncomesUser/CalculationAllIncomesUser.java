package ru.top.java212.calculationExpensesAndIncomesUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.top.java212.dao.AllIncomesUser;
import ru.top.java212.dao.IncomeAmount;
import ru.top.java212.dao.IncomeDbDao;
import ru.top.java212.dao.TotalIncome;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculationAllIncomesUser implements AllIncomesUser {

    private final IncomeDbDao incomeDao;

    @Autowired
    public CalculationAllIncomesUser(IncomeDbDao incomeDao) {
        this.incomeDao = incomeDao;
    }

    @Override
    public int calculationIncomesUser(int userId, LocalDate startDate, LocalDate endDate){

        List<IncomeAmount> list = incomeDao.findByUserIdAndDateBetween(userId, startDate, endDate);
        return list.stream().mapToInt(IncomeAmount::getIncomeAmount).reduce(0, Integer :: sum);
    }
    @Override
    public Map<String, Long> calculationIncomesUserBySource(int userId, LocalDate startDate, LocalDate endDate){
        List<TotalIncome> list = incomeDao.getIncomesUserByCategory(userId, startDate, endDate);
        Map<String, Long> map = new HashMap<>();
        for (TotalIncome i : list){
            map.put(i.getCategoryName(), i.getTotal());
        }
        return map;
    }
}
