package ru.top.java212.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "periods")
public class Period {
    @Id
    @Column(name = "period_id")
    private int id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "stop-date")
    private LocalDate stopDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Order order;

    Period(){
    }
    public Period(int id){
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
    public int getId(){
        return this.id;
    }
}
