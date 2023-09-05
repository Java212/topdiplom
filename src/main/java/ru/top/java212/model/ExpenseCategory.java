package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="expenses_category")

@NamedQuery(
        name = "selectAllExpenseCategoryForPeriod", query = "select n from ExpenseCategory n where n.dataExpenseCategory between : startPeriod and : endPeriod"
)

public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_category_id")
    private Integer id;

    @Column(name = "name_expense_category")
    private String nameExpenseCategory;

    @Column(name = "amount_expense_category")
    private int amountExpenseCategory;

    @Column(name = "data_expense_category")
    private LocalDate dataExpenseCategory;

    private ExpenseCategory() {
    }

    public ExpenseCategory(String nameExpenseCategory) {
        this.nameExpenseCategory = nameExpenseCategory;
    }

    public ExpenseCategory(String nameExpenseCategory, int amountExpenseCategory) {
        this.nameExpenseCategory = nameExpenseCategory;
        this.amountExpenseCategory = amountExpenseCategory;
        this.dataExpenseCategory= LocalDate.now();
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

    public int getAmountExpenseCategory() {
        return amountExpenseCategory;
    }

    public void setAmountExpenseCategory(int amountExpenseCategory) {
        this.amountExpenseCategory = amountExpenseCategory;
    }

    public LocalDate getDataExpenseCategory() {
        return dataExpenseCategory;
    }

    public void setDataExpenseCategory(LocalDate dataExpenseCategory) {
        this.dataExpenseCategory = dataExpenseCategory;
    }
}
