package testgroup.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.model.Replacement;
import java.util.List;

@Repository

public class ReplacementDAOImpl implements ReplacementDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Replacement> allReplacement() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Replacement").list();
    }


    @Override
    public void add(Replacement replacement) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(replacement);
    }

    @Override
    public void delete(Replacement replacement) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(replacement);
    }

    @Override
    public void edit(Replacement replacement) {
        Session session = sessionFactory.getCurrentSession();
        session.update(replacement);
    }

    @Override
    public Replacement getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Replacement.class, id);
    }
}
