package ru.top.java212;

import jakarta.persistence.*;

@Entity
@Table(name="pets")
public class PetEntity {

    @Column(name = "pet_id")
    @Id
    private Long id;

    private String name;

    private String tag;

    PetEntity() {
    }

    public PetEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
