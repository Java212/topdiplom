package ru.top.java212.model;


import jakarta.persistence.*;

@Entity
@Table(name = "tools")
public class Tool{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tools_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "location")
    private String location;


    public Tool(Long id, String title, String description, int price, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.location = location;
    }

    public Tool(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}