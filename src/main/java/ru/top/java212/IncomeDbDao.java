package ru.top.java212;

import org.springframework.data.repository.CrudRepository;
import ru.top.java212.model.Income;

public interface IncomeDbDao extends CrudRepository<Income, Integer> {
}
