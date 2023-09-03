package ru.top.java212.model;

import jakarta.persistence.*;


import java.util.List;


@Entity
@Table(name = "my_orders")
public class MyOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_order_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "myOrders", cascade = CascadeType.ALL)
    private List<Order> orders;

    public MyOrders(){}
    public MyOrders(User user, List<Order> orders) {

        this.user = user;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
