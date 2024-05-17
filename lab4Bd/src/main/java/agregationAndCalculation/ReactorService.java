package agregationAndCalculation;


import entities.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReactorService {

    private ReactorRepository reactorRepository;

    public ReactorService(ReactorRepository reactorRepository) {
        this.reactorRepository = reactorRepository;
    }
    public Map<String, Map<Integer, Double>> calculateReactorEnergyByCountryAndYear() {
        EnergyCalculator energyCalculator = new EnergyCalculator();
        ArrayList<Countries> results = reactorRepository.findReactorsByCountries();
        Map<String, Map<Integer, Double>> energyByCountryAndYear = new HashMap<>();
        for (Countries country : results) {
            List<Reactors> reactors = country.getReactors();
            Map<Integer, Double> energyForCountry = energyCalculator.calculateEnergy(reactors);
            energyByCountryAndYear.put(country.getName(), energyForCountry);
        }
        Map<String, Map<Integer, Double>> sortedMapByYear = sortedMapByYear(energyByCountryAndYear);
        return sortedMapByYear;
    }

    private Map<String, Map<Integer, Double>> sortedMapByYear(Map<String, Map<Integer, Double>> energyByCountryAndYear) {
        List<Map.Entry<String, Map<Integer, Double>>> countryEntries = new ArrayList<>(energyByCountryAndYear.entrySet());
        countryEntries.forEach(countryEntry -> {
            Map<Integer, Double> sortedYearToEnergy = countryEntry.getValue()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
            energyByCountryAndYear.put(countryEntry.getKey(), sortedYearToEnergy);
        });
        return energyByCountryAndYear;
    }

    public Map<String, Map<Integer, Double>> calculateReactorEnergyByRegionAndYear() {
         EnergyCalculator energyCalculator = new EnergyCalculator();
        ArrayList<Regions> results = reactorRepository.findReactorsByRegions();
        Map<String, Map<Integer, Double>> energyByCountryAndYear = new HashMap<>();
        for (Regions region : results) {
            List<Countries> countries = region.getCountries();
            for (Countries country : countries) {
                List<Reactors> reactors = country.getReactors();
                Map<Integer, Double> energyForRegion = energyCalculator.calculateEnergy(reactors);
                energyByCountryAndYear.put(region.getName(), energyForRegion);
            }
        }
        Map<String, Map<Integer, Double>> sortedMapByYear = sortedMapByYear(energyByCountryAndYear);
        return sortedMapByYear;
    }

    public Map<String, Map<Integer, Double>> calculateReactorEnergyByOperator() {
        EnergyCalculator energyCalculator = new EnergyCalculator();
        ArrayList<Operators> results = reactorRepository.findReactorsByOperator();
        Map<String, Map<Integer, Double>> energyByCountryAndYear = new HashMap<>();
        for (Operators operator : results) {
            List<Reactors> reactors = operator.getReactors();
            Map<Integer, Double> energyForCountry = energyCalculator.calculateEnergy(reactors);
            energyByCountryAndYear.put(operator.getName(), energyForCountry);
        }
        Map<String, Map<Integer, Double>> sortedMapByYear = sortedMapByYear(energyByCountryAndYear);
        return sortedMapByYear;
    }

    public Map<String, Map<Integer, Double>> calculateReactorEnergyByOwner() {
        EnergyCalculator energyCalculator = new EnergyCalculator();
        ArrayList<Owners> results = reactorRepository.findReactorsByOwner();
        Map<String, Map<Integer, Double>> energyByCountryAndYear = new HashMap<>();
        for (Owners owner : results) {
            List<OwnersAndReactors> ownerAndReactors = owner.getOwnersAndReactors();
            List<Reactors> reactors = new ArrayList<>();
            for (OwnersAndReactors ownerAndReactor : ownerAndReactors) {
                Reactors reactor = ownerAndReactor.getReactor();
                reactors.add(reactor);
            }
            Map<Integer, Double> energyForOwner = energyCalculator.calculateEnergy(reactors);
            energyByCountryAndYear.put(owner.getName(), energyForOwner);
        }
        Map<String, Map<Integer, Double>> sortedMapByYear = sortedMapByYear(energyByCountryAndYear);
        return sortedMapByYear;
    }
}
