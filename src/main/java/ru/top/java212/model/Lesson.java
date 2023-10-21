package ru.top.java212.model;

public class Lesson {

    private String disciplineName;
    private String teacherName;
    public Lesson(String disciplineName, String teacherName){
        this.disciplineName = disciplineName;
        this.teacherName = teacherName;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public String getTeacherName() {
        return teacherName;
    }
}
