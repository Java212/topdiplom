package ru.top.java212;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Expense;

public interface ExpenseDbDao extends CrudRepository<Expense, Integer> {
}
