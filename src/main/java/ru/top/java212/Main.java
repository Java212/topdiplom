package ru.top.java212;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LocalDate startDate = LocalDate.of(2023,10,10);
        LocalDate stopDate = LocalDate.of(2023,10,16);
        LocalDate orderStartDate = LocalDate.of(2023,10,5);
        LocalDate orderStopDate = LocalDate.of(2023,10,10);
        if((orderStartDate.isBefore(startDate) && orderStopDate.isBefore(startDate)) || (orderStartDate.isAfter(stopDate) && orderStopDate.isAfter(stopDate))){
            System.out.println("true");
        } else{
            System.out.println("false");
        }

    }

}