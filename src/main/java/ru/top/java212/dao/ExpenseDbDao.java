package ru.top.java212.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Expense;
import ru.top.java212.model.ExpenseAmount;
import ru.top.java212.model.TotalExpense;

import java.time.LocalDate;
import java.util.List;



public interface ExpenseDbDao extends CrudRepository<Expense, Integer> {

    List<ExpenseAmount> findByDateBetween(LocalDate initialDate, LocalDate endDate);
    List<ExpenseAmount> findByUserIdAndDateBetween(int userId, LocalDate initialDate, LocalDate endDate);

    @Query("select expenseCategory.nameExpenseCategory as categoryName, sum(expenseAmount) as total \n" +
            "                  from Expense where date between ?2 and ?3 and user.id = ?1 \n" +
            "                  group by expenseCategory.nameExpenseCategory")
    List<TotalExpense> getExpensesUserByCategory(int userId, LocalDate initialDate, LocalDate endData);

    @Query("select expenseCategory.nameExpenseCategory as categoryName, sum(expenseAmount) as total \n" +
            "                  from Expense where date between ?1 and ?2 \n" +
            "                  group by expenseCategory.nameExpenseCategory")
    List<TotalExpense> getExpensesFamilyByCategory(LocalDate initialDate, LocalDate endData);

    @Query("select sum(expenseAmount) from Expense where expenseCategory.nameExpenseCategory=?1")
    int getAllAmountForTheDeletedCategory(String removeExpenseCategory);

    @Query("select count(*) from Expense where expenseCategory.nameExpenseCategory !=?1")
    int getCountRecordsInDbWithoutRemoveCategory(String removeExpenseCategory);

    @Modifying
    @Query("update Expense  set expenseAmount = (expenseAmount + ?1) where expenseAmount >0")
    void transferringTheAmountOfExpensesFromTheDeletedCategory(long amountToAdd);
}