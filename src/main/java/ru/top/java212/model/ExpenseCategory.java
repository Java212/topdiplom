package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table(name="expenses_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_category_id")
    private Integer id;

    @Column(name = "name_expense_category")
    private String nameExpenseCategory;

    private ExpenseCategory() {
    }

    public ExpenseCategory(String nameExpenseCategory) {
        this.nameExpenseCategory = nameExpenseCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameExpenseCategory() {
        return nameExpenseCategory;
    }

    public void setNameExpenseCategory(String nameExpenseCategory) {
        this.nameExpenseCategory = nameExpenseCategory;
    }



}
