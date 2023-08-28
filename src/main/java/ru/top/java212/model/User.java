package ru.top.java212.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private  Integer id;

    @Column(name = "user_name")
     private String name;

    @Column(name = "user_login")
    private String login;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_role")
    private String role;

    @ManyToOne
    @JoinColumn(
            name = "budget_id",
            updatable = false
    )
    private Budget budget;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_expenses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="expense_category_id", nullable = false)
    )
    private Set<ExpenseCategory> expenses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_incomes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="income_id", nullable = false)
    )
    private Set<ExpenseCategory> incomes;

    public User(){};

    public User(Integer id) {
        this.id=id;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Set<ExpenseCategory> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<ExpenseCategory> expenses) {
        this.expenses = expenses;
    }

    public Set<ExpenseCategory> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<ExpenseCategory> incomes) {
        this.incomes = incomes;
    }
}
