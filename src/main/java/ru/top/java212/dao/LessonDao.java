package ru.top.java212.dao;

import ru.top.java212.model.Lesson;

import java.util.List;

public interface LessonDao {
    List<Lesson> getSubjectsWithTeacher();
}
