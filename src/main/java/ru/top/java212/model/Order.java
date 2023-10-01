package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    @JoinColumn(name = "person_id")
    private Person person;


    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "stop_date")
    private LocalDate stopDate;

    Order(Person person, Tool tool, LocalDate startDate, LocalDate stopDate ){
        this.person = person;
        this.tool = tool;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }
    public Order(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }
}
