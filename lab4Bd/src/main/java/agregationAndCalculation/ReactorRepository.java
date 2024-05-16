package agregationAndCalculation;
import entities.Countries;
import entities.Operators;
import entities.Owners;
import entities.Reactors;
import entities.Regions;
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

    public ArrayList<ArrayList<Reactors>> findReactorsByOwnerAndOperator() {
        ArrayList<ArrayList<Reactors>> result = new ArrayList<>();
        String hql = "select ow, op, count(r) as reactor_counts " +
                "FROM Reactors r " +
                "inner join r.owner ow " +
                "inner join r.operator op " +
                "group by op, ow " +
                "order by reactor_counts desc";
        Query<Object[]> query = session.createQuery(hql, Object[].class);
        for (Object[] row : query.list()) {
            List<Reactors> owner_reactors = ((Owners) row[0]).getReactors();
            List<Reactors> operator_reactors = ((Operators) row[1]).getReactors();
            List<Reactors> reactors = owner_reactors.stream()
                    .distinct()
                    .filter(operator_reactors::contains)
                    .toList();
            result.add(new ArrayList<>(reactors));
        }
        return result;
    }
}
