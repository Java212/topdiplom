package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @Column(name ="tool_id")
    private int toolId;

    @Column(name = "user_id")
    private int userId;

    private Period period;

    Order(){
    }
    public Order(int id){
        this.id = id;
    }


}
