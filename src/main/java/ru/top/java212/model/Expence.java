package ru.top.java212.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "expences")
public class Expence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double summ;

    private Integer userId;

    private Integer categoryId;

    private LocalDate date;

    public Expence(String name, Double summ, Integer userId, Integer categoryId, LocalDate date) {
        this.name = name;
        this.summ = summ;
        this.userId = userId;
        this.categoryId = categoryId;
        this.date = date;
    }
}
