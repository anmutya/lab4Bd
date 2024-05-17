package agregationAndCalculation;
import entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReactorRepository {
    private final Session session;

    public ReactorRepository(Session session) {
        this.session = session;
    }

    public ArrayList<Countries> findReactorsEnergyByCountries() {
        ArrayList<Countries> countries = new ArrayList<>();
        String hql = "SELECT c " +
                "FROM Countries c";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        for (Object[] row : query.list()) {
            countries.add((Countries) row[0]);
        }
        return countries;
    }

    public ArrayList<Regions> findReactorsEnergyByRegions() {
        ArrayList<Regions> regions = new ArrayList<>();
        String hql = "SELECT r " +
                "FROM Regions r";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        for (Object[] row : query.list()) {
            regions.add((Regions) row[0]);
        }
        return regions;
    }

    public ArrayList<Operators> findReactorsByOperator() {
        ArrayList<Operators> operators = new ArrayList<>();
        String hql = "SELECT r " +
                "FROM Operators r";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        for (Object[] row : query.list()) {
            operators.add((Operators) row[0]);
        }
        return operators;
    }
    public ArrayList<Owners> findReactorsByOwner() {
        ArrayList<Owners> owners = new ArrayList<>();
        String hql = "SELECT ow " +
                "FROM Owners ow "+
                "inner join ow.ownersAndReactors";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        for (Object[] row : query.list()) {
            owners.add((Owners) row[0]);
        }
        return owners;
    }
}
