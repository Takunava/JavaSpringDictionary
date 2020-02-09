package system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import system.model.ModelDictionary;
import system.model.Phrase;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class DaoDictionary implements DaoInterface{

    public DaoDictionary() {
    }

    @Override
    public Collection getPhrases() {
        List<Phrase> phrases = new ArrayList<>();
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query q = session.createQuery("FROM dictbd.modeldictionary_phrases");

        phrases = q.getResultList();

        session.close();
        sessionFactory.close();
        return phrases;
    }

    @Override
    public void addPhrase(ModelDictionary dict) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(dict);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }

    @Override
    public void uploadPhrase(ModelDictionary dict) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.update(dict);
        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
    }






    /*
сделать родключение БД
 */

}
