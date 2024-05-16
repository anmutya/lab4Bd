package agregationAndCalculation;


import entities.Countries;
import entities.Reactors;
import entities.Regions;
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
        ArrayList<Countries> results = reactorRepository.findReactorsEnergyByCountries();
        Map<String, Map<Integer, Double>> energyByCountryAndYear = new HashMap<>();
        for (Countries country : results) {
            List<Reactors> reactors = country.getReactors();
            Map<Integer, Double> energyForCountry = energyCalculator.calculateEnergy(reactors);
            energyByCountryAndYear.put(country.getName(), energyForCountry);
        }
        Map<String, Map<Integer, Double>> sortedMapByYear = sortedMapByYear(energyByCountryAndYear);
        sortedMapByYear.forEach((country, yearToEnergy)
                -> yearToEnergy.forEach((year, energy)
                        -> System.out.println("Country: " + country + ", Year: " + year + ", Energy: " + energy)));
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
        ArrayList<Regions> results = reactorRepository.findReactorsEnergyByRegions();
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
        sortedMapByYear.forEach((region, yearToEnergy)
                -> yearToEnergy.forEach((year, energy)
                        -> System.out.println("Region: " + region + ", Year: " + year + ", Energy: " + energy)));
        return energyByCountryAndYear;
    }

    public Map<String, Map<String, Map<Integer, Double>>> calculateReactorEnergyByOwnerAndOperator() {
        EnergyCalculator energyCalculator = new EnergyCalculator();
        ArrayList<ArrayList<Reactors>> results = reactorRepository.findReactorsByOwnerAndOperator();
        Map<String, Map<String, Map<Integer, Double>>> energyByCountryAndYear = new HashMap<>();
        for (ArrayList<Reactors> reactors : results) {
            String ownerName = reactors.get(0).getOwner().getName();
            String operatorName = reactors.get(0).getOperator().getName();
            Map<Integer, Double> energyForRegion = energyCalculator.calculateEnergy(reactors);
            energyByCountryAndYear
                    .computeIfAbsent(ownerName, k -> new HashMap<>())
                    .computeIfAbsent(operatorName, k -> new HashMap<>())
                    .putAll(energyForRegion);
        }
        energyByCountryAndYear.forEach((owner, operatorToEnergyMap)
                -> operatorToEnergyMap.forEach((operator, yearToEnergy)
                        -> yearToEnergy.forEach((year, energy)
                        -> System.out.println("Owner: " + owner + ", Operator: " + operator + ", Year: " + year + ", Energy: " + energy))));
        return energyByCountryAndYear;
    }
}
