package ru.top.java212;

public class SubjectDTO {

    private final Integer id;
    private final String name;

    private final Integer teacher_id;


    public SubjectDTO(Integer id, String name, Integer teacher_id) {
        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }
}
