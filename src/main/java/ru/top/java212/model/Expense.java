package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table( name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "expense _id")
    private Integer id;
    @Column(name = "expense_amount")
    private int expenseAmount;

    @Column(name = "date_expense")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Expense(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }
}
