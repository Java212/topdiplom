package ru.top.java212.model;

public class Lesson {
    private String subjectName;
    private String teacherName;
    public Lesson(String subjectName, String teacherName){
        this.subjectName = subjectName;
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }


}
