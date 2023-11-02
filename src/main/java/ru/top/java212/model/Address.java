package ru.top.java212.model;

import jakarta.persistence.*;


@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", insertable = false)
    private Integer id;


    private String district;

    private String street;

    private int numberOfHouse;

    private int apartmentNumber;

    public Address() {
    }

    public Address(Integer id, String district, String street, int numberOfHouse, int apartmentNumber) {
        this.id = id;
        this.district = district;
        this.street = street;
        this.numberOfHouse = numberOfHouse;
        this.apartmentNumber = apartmentNumber;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public int getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(int numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
