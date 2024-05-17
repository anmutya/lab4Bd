package entityForDataBase;

import entities.*;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExportEntitiesToDB {
    private Session session;
    public ExportEntitiesToDB(Session session) {
        this.session = session;
    }

    public void exportReactorsToDB(ArrayList<Reactors> reactors) {
        session.beginTransaction();
        for (Reactors react : reactors) {
            session.persist(react);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void exportOwnersToDB(ArrayList<Owners> owners) {
        session.beginTransaction();
        for (Owners owner : owners) {
            session.persist(owner);
        }
        session.flush();
        session.getTransaction().commit();
    }
    public void exportOwnersAndReactorsToDB(ArrayList<OwnersAndReactors> owners) {
        session.beginTransaction();
        for (OwnersAndReactors owner : owners) {
            session.persist(owner);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void exportOperatorsToDB(ArrayList<Operators> operators) {
        session.beginTransaction();
        for (Operators operator : operators) {
            session.persist(operator);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void exportCountriesToDB(ArrayList<Countries> countries) {
        session.beginTransaction();
        for (Countries country : countries) {
            session.persist(country);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void exportRegionsToDB(ArrayList<Regions> regions) {
        session.beginTransaction();
        for (Regions region : regions) {
            session.persist(region);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void exportStatusToDB(ArrayList<Status> statuses) {
        session.beginTransaction();
        for (Status status : statuses) {
            session.persist(status);
        }
        session.flush();
        session.getTransaction().commit();
    }

    public void exportKiumsToDB(ArrayList<Kium> kiums) throws SQLException {
        session.beginTransaction();
        for (Kium kium : kiums) {
            session.persist(kium);
        }
        session.flush();
        session.getTransaction().commit();
    }

}
