package testgroup.dao;

import testgroup.model.Lesson;

import java.util.List;

public interface LessonDAO {
    List<Lesson> allLessons();
    List<Lesson> allLessonGroup(String s);
    List<Lesson> allLessonTeacher(String s);
    List<String> allGroup();
    List<String> allTeacher();
    void add(Lesson lesson);
    void delete(Lesson lesson);
    void edit(Lesson lesson);
    Lesson getById(int id);
}
