package ru.top.java212.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tools")
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tool_id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "person_id")
    private Person person;
    private String name;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id",nullable = false)
    private Address address;

    private double price;

    @OneToMany(mappedBy = "tool")
    private List<Order> orders;

    @Column(name = "in_rent")
    private boolean inRent;

    Tool(){
    }
    public Tool(String name, Person person, Address address,double price){
        this.name = name;
        this.person = person;
        this.address = address;
        this.price = price;
        this.inRent = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isInRent() {
        return inRent;
    }

    public void setInRent(boolean inRent) {
        this.inRent = inRent;
    }
}
