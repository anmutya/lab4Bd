    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reactor;

/**
 *
 * @author annamutovkina
 */
public class Reactor {
    private String type;
    private double burnup;
    private double kpd;
    private double enrichment;
    private int thermal_capacity;
    private double electrical_capacity;
    private int life_time;
    private double first_load;
    private String source;
    public Reactor() {
    }
    public Reactor(String type, double burnup, double kpd, double enrichment, int thermal_capacity, double electrical_capacity, int life_time, double first_load) {
        this.type = type;
        this.burnup = burnup;
        this.kpd = kpd;
        this.enrichment = enrichment;
        this.thermal_capacity = thermal_capacity;
        this.electrical_capacity = electrical_capacity;
        this.life_time = life_time;
        this.first_load = first_load;
    }
    public String getType() {
        return type;
    }
    public double getBurnup() {
        return burnup;
    }
    public double getKpd() {
        return kpd;
    }
    public double getEnrichment() {
        return enrichment;
    }
    public double getThermal_Capacity() {
        return thermal_capacity;
    }
    public double getElectrical_Capacity() {
        return electrical_capacity;
    }
    public int getLifeTime() {
        return life_time;
    }
    public double getFirstLoad() {
        return first_load;
    }
    public void setType(String class_type) {
        this.type = class_type;
    }
    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }
    public void setKpd(double kpd) {
        this.kpd = kpd;
    }
    public void setEnrichment(double enrichment) {
        this.enrichment = enrichment;
    }
    public void setSource(String source){
        this.source = source;
    }
    public void setThermal_capacity(int thermal_capacity) {
        this.thermal_capacity = thermal_capacity;
    }
    public void setElectrical_capacity(double electrical_capacity) {
        this.electrical_capacity = electrical_capacity;
    }
    public void setLife_time(int life_time) {
        this.life_time = life_time;
    }
    public void setFirst_load(double first_load) {
        this.first_load = first_load;
    }
        @Override
    public String toString() {
        return "burnup=" + burnup + ", kpd=" + kpd + ", enrichment=" + 
                enrichment + ", thermal_capacity=" + thermal_capacity + ", electrical_capacity=" + electrical_capacity +
                ", life_time=" + life_time + ", first_load=" + first_load+ ", source= "+ source;
    }

}
