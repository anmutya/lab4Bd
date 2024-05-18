package agregationAndCalculation;

import entities.Kium;
import entities.Reactors;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import reactor.Reactor;
import reactor.ReactorStorage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author annamutovkina
 */
public class EnergyCalculator {

    public Map<Integer, Double> calculateEnergy(List<Reactors> reactors) {
        Map<Integer, Double> totalEnergyByYear = new HashMap<>();
        for (Reactors reactor : reactors) {
            Map<Integer, Double> energyForKnownYears = null;
            Map<Integer, Double> energyForMissingYears = null;
            if (reactor.getEnergyByYear().isEmpty()) {
                energyForKnownYears = calculateEnergyForKnownYears(reactor);
                energyForMissingYears = calculateEnergyForMissinigYears(reactor);
                energyForKnownYears.forEach((key, value) -> totalEnergyByYear.merge(key, value, Double::sum));
                energyForMissingYears.forEach((key, value) -> totalEnergyByYear.merge(key, value, Double::sum));
                reactor.setEnergyByYear((HashMap<Integer, Double>) totalEnergyByYear);
            } else {
                energyForKnownYears = reactor.getEnergyByYear();
                energyForKnownYears.forEach((key, value) -> totalEnergyByYear.merge(key, value, Double::sum));
            }
        }
        return totalEnergyByYear;
    }

    private Map<Integer, Double> calculateEnergyForMissinigYears(Reactors reactor) {
        List<Integer> missingYears = calculateMissingYears(reactor.getKiums());
        Map<Integer, Double> energyByYear = new HashMap<>();
        int shutdownYear = dataFormatToOnlyYear(reactor.getShutdownDate());
        int firstGridYear = dataFormatToOnlyYear(reactor.getFirstGridConnection());
        for (Integer year : missingYears) {
            double energy;
            if (shutdownYear > year && firstGridYear < year) {
                double loadFactor = 0.85;
                energy = loadFactor * reactor.getThermalCapacity() / findBurnup(reactor);
            } else if (firstGridYear == year) {
                energy = findFirstLoad(reactor);
            } else {
                energy = 0;
            }
            energyByYear.merge(year, energy, Double::sum);
        }
        return energyByYear;
    }

    private double findBurnup(Reactors reactors) {
        HashMap<String, ArrayList<Reactor>> reactorStorage = ReactorStorage.INSTANCE.getReactors();
        double burnup = 0;
        if(reactors.getEnergyByYear().isEmpty()){
            Set<String> keySet = reactorStorage.keySet();
            String keyAtIndex0 = (String) keySet.toArray()[0];
            ArrayList<Reactor> reactorListAtIndex0 = reactorStorage.get(keyAtIndex0);
            for (Reactor reactor : reactorListAtIndex0) {
                if (Objects.equals(reactor.getType(), reactors.getType())) {
                    burnup = reactor.getBurnup();
                } else if (Objects.equals(reactors.getType(), "LWGR") && Objects.equals(reactor.getType(), "RBMK")) {
                    burnup = reactor.getBurnup();
                } else if (Objects.equals(reactors.getType(), "GCR") && Objects.equals(reactor.getType(), "MAGNOX")) {
                    burnup = reactor.getBurnup();
                } else if (Objects.equals(reactors.getType(), "FBR") && Objects.equals(reactor.getType(), "BN")) {
                    burnup = reactor.getBurnup();
                }
            }
        }

        return burnup;
    }

    private double findFirstLoad(Reactors reactors) {
        double firstLoad = 0;
        HashMap<String, ArrayList<Reactor>> reactorStorage = ReactorStorage.INSTANCE.getReactors();
        if(reactors.getEnergyByYear().isEmpty()){
            Set<String> keySet = reactorStorage.keySet();
            String keyAtIndex0 = (String) keySet.toArray()[0];
            ArrayList<Reactor> reactorListAtIndex0 = reactorStorage.get(keyAtIndex0);
            for (Reactor reactor : reactorListAtIndex0) {
                if (Objects.equals(reactor.getType(), reactors.getType())) {
                    firstLoad = reactor.getFirstLoad();
                } else if (Objects.equals(reactors.getType(), "LWGR") && Objects.equals(reactor.getType(), "RBMK")) {
                    firstLoad = reactor.getFirstLoad();
                } else if (Objects.equals(reactors.getType(), "GCR") && Objects.equals(reactor.getType(), "MAGNOX")) {
                    firstLoad = reactor.getFirstLoad();
                } else if (Objects.equals(reactors.getType(), "FBR") && Objects.equals(reactor.getType(), "BN")) {
                    firstLoad = reactor.getFirstLoad();
                }
            }
        }

        return firstLoad;
    }

    private List<Integer> calculateMissingYears(List<Kium> kiums) {
        List<Integer> yearsKium = kiums.stream().map(Kium::getYear).toList();
        int[] years = {2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024};
        Set<Integer> setOfYearsKium = new HashSet<>(yearsKium);
        List<Integer> missingYears = Arrays.stream(years)
                .boxed()
                .filter(year -> !setOfYearsKium.contains(year))
                .toList();
        return missingYears;
    }

    private Integer dataFormatToOnlyYear(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int year = date != null ? Integer.parseInt(dateFormat.
                format(date).substring(0, 4)) : Integer.MAX_VALUE;
        return year;
    }

    private Map<Integer, Double> calculateEnergyForKnownYears(Reactors reactor) {
        Map<Integer, Double> energyByYear = new HashMap<>();
        int shutdownYear = dataFormatToOnlyYear(reactor.getShutdownDate());
        int firstGridYear = dataFormatToOnlyYear(reactor.getFirstGridConnection());
        if(!reactor.getKiums().isEmpty()) {
            for (Kium kium : reactor.getKiums()) {
                double energy = 0;
                double loadFactor = 0;
                if (shutdownYear > kium.getYear() && firstGridYear < kium.getYear()) {
                    loadFactor = kium.getLoadFactor() == null ? 0.85 : kium.getLoadFactor()/100;
                    energy = loadFactor * reactor.getThermalCapacity() / findBurnup(reactor);
                } else if (firstGridYear == kium.getYear()) {
                    if(kium.getLoadFactor() == null){
                        energy = findFirstLoad(reactor);
                    }
                    else {
                        loadFactor = kium.getLoadFactor();
                        energy = 3 * loadFactor * reactor.getThermalCapacity() / findBurnup(reactor);
                    }
                }
                energyByYear.merge(kium.getYear(), energy, Double::sum);
            }
        }
        return energyByYear;
    }
}
