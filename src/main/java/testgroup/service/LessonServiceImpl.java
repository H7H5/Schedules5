package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.dao.LessonDAO;
import org.springframework.transaction.annotation.Transactional;
import testgroup.model.Lesson;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    LessonDAO lessonDAO;

    @Autowired
    public void setFilmService(LessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @Override
    @Transactional
    public List<Lesson> allLesson() {
        return lessonDAO.allLessons();
    }

    @Override
    @Transactional
    public List<Lesson> allLessonGroup(String st) {
        return lessonDAO.allLessonGroup(st);
    }

    @Override
    @Transactional
    public List<Lesson> allLessonTeacher(String st) {
        return lessonDAO.allLessonTeacher(st);
    }

    @Override
    @Transactional
    public List<String> allGroup() {
        return lessonDAO.allGroup();
    }

    @Override
    @Transactional
    public List<String> allTeacher() {
        return lessonDAO.allTeacher();
    }

    @Override
    @Transactional
    public void add(Lesson lesson) {
        lessonDAO.add(lesson);
    }

    @Override
    @Transactional
    public void delete(Lesson lesson) {
        lessonDAO.delete(lesson);
    }

    @Override
    @Transactional
    public void edit(Lesson lesson) {
        lessonDAO.edit(lesson);
    }

    @Override
    @Transactional
    public Lesson getById(int id) {
        return lessonDAO.getById(id);
    }
}
