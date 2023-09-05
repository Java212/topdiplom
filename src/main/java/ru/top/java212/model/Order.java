package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "tool_id")
    private Tool tool;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id",nullable = false)
    private Period period;

    Order(){
    }
    public Order(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
