/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

/**
 *
 * @author annamutovkina
 */
@Entity
public class Status {

    public List<Reactors> getReactors() {
        return reactors;
    }

    public void setReactors(List<Reactors> reactors) {
        this.reactors = reactors;
    }

    @Id
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "status")
    private List<Reactors> reactors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status() {
    }
}
