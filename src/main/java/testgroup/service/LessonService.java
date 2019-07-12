package testgroup.service;

import testgroup.model.Lesson;

import java.util.List;

public interface LessonService {
    List<Lesson> allLesson();
    List<Lesson> allLessonGroup(String st);
    List<Lesson> allLessonTeacher(String st);
    List<String> allGroup();
    List<String> allTeacher();
    void add(Lesson lesson);
    void delete(Lesson lesson);
    void edit(Lesson lesson);
    Lesson getById(int id);
}
