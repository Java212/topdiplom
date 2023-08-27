package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "expense_category_id")
    private Integer id;

    @Column(name = "name_expense_category")
    private String nameExpenseCategory;

    @Column(name = "expense_amount")
    private int expenseAmount;

    @Column(name = "date_expense")
    private LocalDate date;

    @ManyToMany(mappedBy = "expenses", fetch = FetchType.LAZY)
    private Set<User> users;

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

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public ExpenseCategory(Integer id, String descriptionExpenseCategory) {
        this.id = id;
        this.nameExpenseCategory = descriptionExpenseCategory;
    }
}
