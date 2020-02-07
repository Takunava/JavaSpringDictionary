package system.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import system.model.ModelDictionary;
import system.model.Phrase;

import java.util.List;

@Repository
public class DaoDictionary implements DaoInterface{


    public DaoDictionary() {
      /*  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        session.close();
        sessionFactory.close();*/
    }

    @Override
    public List<Phrase> getPrases() {
        return null;
    }

    @Override
    public void setPrases() {

    }




    /*
сделать родключение БД
 */

}
