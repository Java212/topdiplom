package ru.top.java212;

public class SubjectDTO {

    private final Integer id;
    private final String name;


    public SubjectDTO(String name, Integer id) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
