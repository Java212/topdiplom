package ru.top.java212;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.ExpenseCategory;

public interface ExpenseCategoryDbDao extends CrudRepository<ExpenseCategory, Integer> {
}
