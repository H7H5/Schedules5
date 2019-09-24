package testgroup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.model.Lesson;
import java.util.List;

@Repository
public class LessonDAOImpl implements LessonDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> allLessons() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Lesson").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> allLessonGroup(String gr) {
        String str = gr;
        String sql = "FROM Lesson D WHERE D.groupp = '"+str+"'";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(sql).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> allLessonTeacher(String te) {
        List<Lesson> lessons=allLessonTeacher1(te);
        List<Lesson> lessons2=allLessonTeacher2(te);
        for (int i =0;i <lessons2.size();i++){
            lessons.add(lessons2.get(i));
        }
        return lessons;
    }
    public List<Lesson> allLessonTeacher1(String te) {
        String str = te+".";
        String sql = "FROM Lesson D WHERE D.teacher = '"+str+"'";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(sql).list();
    }
    public List<Lesson> allLessonTeacher2(String te) {
        String str = te+".";
        String sql = "FROM Lesson D WHERE D.teacher2 = '"+str+"'";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(sql).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> allGroup() {
        String sql = "select groupp from Lesson";
        Session session = sessionFactory.getCurrentSession();
        List<String> groups = session.createQuery(sql).list();
        return groups;
    }

    @Override
    public List<String> allTeacher() {
        String sql = "select teacher from Lesson";
        Session session = sessionFactory.getCurrentSession();
        List<String> teachers = session.createQuery(sql).list();
        String sql1 = "select teacher2 from Lesson";
        Session session1 = sessionFactory.getCurrentSession();
        List<String> teachers2 = session1.createQuery(sql1).list();
        for (int i = 0;i<teachers2.size();i++){
            if (teachers2.get(i)==null) {

            }else if (teachers2.get(i).equals("")){

            }else {
                teachers.add(teachers2.get(i));
            }
        }
        return teachers;
    }

    @Override
    public void add(Lesson lesson) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(lesson);
    }
    @Override
    public void delete(Lesson lesson) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(lesson);
    }
    @Override
    public void edit(Lesson lesson) {
        Session session = sessionFactory.getCurrentSession();
        session.update(lesson);
    }
    @Override
    public Lesson getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Lesson.class, id);
    }
}
