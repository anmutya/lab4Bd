package managers;

import entities.Countries;
import entities.Kium;
import entities.Operators;
import entities.Owners;
import entities.Reactors;
import entities.Regions;
import entities.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    private Session session;

    public Session getSession() {
        return session;
    }

    public SessionManager() {
        this.session = createSession();
    }

    public Session createSession() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Operators.class)
                .addAnnotatedClass(Status.class)
                .addAnnotatedClass(Reactors.class)
                .addAnnotatedClass(Countries.class)
                .addAnnotatedClass(Kium.class)
                .addAnnotatedClass(Owners.class)
                .addAnnotatedClass(Regions.class)
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        this.session = sessionFactory.openSession();
        return session;
    }

    public void closeSession() {
        session.close();
    }

}
