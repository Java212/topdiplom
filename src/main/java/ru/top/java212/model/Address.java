package ru.top.java212.model;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @Column(name = "address_id")
    private int id;
    private String district;
    private String street;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Tool tool;

    Address(){

    }
    public Address(String district,String street){
        this.id = 0;
        this.district = district;
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
