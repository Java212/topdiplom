package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table( name = "expenses")

@NamedQuery(
        name="selectAllExpenses", query = "select n.expenseAmount from Expense n where n.date between : startData and : endData"
)
//todo как сделать поиск расходов по  пользователю
//@NamedQueries({
//        @NamedQuery(name="selectAllExpenses", query = "select n.expenseAmount from Expense n where n.date between : startData and : endData"),
//        @NamedQuery(name="selectAllExpensesUser", query = "select n.expenseAmount from Expense n where n.date between : startData and : endData and "),
//})

public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Integer id;
    @Column(name = "expense_amount")
    private int expenseAmount;

    @Column(name = "date_expense")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private Expense(){};


    public Expense(int expenseAmount){
        this.expenseAmount=expenseAmount;
        this.date=LocalDate.now();
    }

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
