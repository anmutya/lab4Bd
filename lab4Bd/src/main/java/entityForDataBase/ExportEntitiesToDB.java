package entityForDataBase;


import entities.Countries;
import entities.Kium;
import entities.Operators;
import entities.Owners;
import entities.Reactors;
import entities.Regions;
import entities.Status;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExportEntitiesToDB {
    public ExportEntitiesToDB() {
    }

    public void extortReactorsToDB(Session session, ArrayList<Reactors> reactors) {
        session.beginTransaction();
        for (Reactors react : reactors) {
            session.persist(react);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void extortOwnersToDB(Session session, ArrayList<Owners> owners) {
        session.beginTransaction();
        for (Owners owner : owners) {
            session.persist(owner);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void extortOperatorsToDB(Session session, ArrayList<Operators> operators) {
        session.beginTransaction();
        for (Operators operator : operators) {
            session.persist(operator);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void extortCountriesToDB(Session session, ArrayList<Countries> countries) {
        session.beginTransaction();
        for (Countries country : countries) {
            session.persist(country);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void extortRegionsToDB(Session session, ArrayList<Regions> regions) {
        session.beginTransaction();
        for (Regions region : regions) {
            session.persist(region);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void extortStatusToDB(Session session, ArrayList<Status> statuses) {
        session.beginTransaction();
        for (Status status : statuses) {
            session.persist(status);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void extortKiumsToDB(Session session, ArrayList<Kium> kiums) throws SQLException {
        session.beginTransaction();
        int count = 0;
        for (Kium kium : kiums) {
            session.persist(kium);
            count++;
            if (count % 25 == 0) {
                session.flush();
                session.clear();
                Logger.getGlobal().log(Level.INFO, "Write " + count + " rows");
            }
        }
        session.flush();
        session.clear();
        session.getTransaction().commit();
    }

}
