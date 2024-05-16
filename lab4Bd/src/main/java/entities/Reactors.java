package entities;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Reactors {
    @Id
    private Integer id;
    private String name;
    private String type;
    private String model;
    @Transient
    private HashMap<Integer, Double> energyByYear = new HashMap<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    public HashMap<Integer, Double> getEnergyByYear() {
        return energyByYear;
    }

    public void setEnergyByYear(HashMap<Integer, Double> energyByYear) {
        this.energyByYear = energyByYear;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owners owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operators operator;
    @Column(name = "thermal_capacity")
    private Integer thermalCapacity;
    @Column(name = "first_grid_connection")
    private Date firstGridConnection;
    @Column(name = "shutdown_date")
    private Date shutdownDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Countries country;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reactor")
    private List<Kium> kiums;


    public List<Kium> getKiums() {
        return kiums;
    }

    public void setKiums(List<Kium> kiums) {
        this.kiums = kiums;
    }

    public Reactors() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public Operators getOperator() {
        return operator;
    }

    public void setOperator(Operators operator) {
        this.operator = operator;
    }

    public Integer getThermalCapacity() {
        return thermalCapacity;
    }

    public void setThermalCapacity(Integer thermalCapacity) {
        this.thermalCapacity = thermalCapacity;
    }

    public Date getFirstGridConnection() {
        return firstGridConnection;
    }

    public void setFirstGridConnection(String firstGridConnection) {
        try {
            this.firstGridConnection = Date.valueOf(firstGridConnection);
        } catch (IllegalArgumentException e) {
//            Logger.getLogger("reactors").log(Level.WARNING, e.getMessage());
        }
    }

    public Date getShutdownDate() {
        return shutdownDate;
    }

    public void setShutdownDate(String shutdownDate) {
        try {
            this.shutdownDate = Date.valueOf(shutdownDate);
        } catch (IllegalArgumentException e) {
//            Logger.getLogger("reactors").log(Level.WARNING, e.getMessage());
        }
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

}
