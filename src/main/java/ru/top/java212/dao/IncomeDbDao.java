package ru.top.java212.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Income;

import java.time.LocalDate;
import java.util.List;

public interface IncomeDbDao extends CrudRepository<Income, Integer> {

    List<IncomeAmount> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<IncomeAmount> findByUserIdAndDateBetween(int userId, LocalDate startDate, LocalDate endDate);
      @Query("select incomeCategory.sourceIncomeCategory as categoryName, sum(incomeAmount) as total \n" +
            "                  from Income where date between ?1 and ?2 \n" +
            "                  group by incomeCategory.sourceIncomeCategory")
    List<TotalIncome> getIncomesFamilyByCategory(LocalDate startDate, LocalDate endDate);

      @Query("select incomeCategory.sourceIncomeCategory as categoryName, sum(incomeAmount) as total \n" +
            "      from Income where date between ?2 and ?3 and user.id = ?1 \n" +
            "      group by incomeCategory.sourceIncomeCategory")

    List<TotalIncome> getIncomesUserByCategory(int userId, LocalDate startDate, LocalDate endDate);

      @Query("select sum(incomeAmount) from Income where incomeCategory.sourceIncomeCategory =?1")
        int getAllAmountForTheDeletedSource(String removeIncomeSource);

    @Query("select count(*) from Income where incomeCategory.sourceIncomeCategory !=?1")
    int getCountRecordsInDbWithoutRemoveSource(String removeIncomeSource);

    @Modifying
    @Query("update Income set incomeAmount = (incomeAmount + ?1) where  incomeAmount >0")
    void transferringTheAmountOfIncomesFromTheDeletedSource(int amountToAdd);
}
