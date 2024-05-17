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

@Entity
/**
 *
 * @author annamutovkina
 */
public class Owners {

    @Id
    private Integer id;
    @Column(name = "name")
    private String name;

//    public List<Reactors> getReactors() {
//        return reactors;
//    }
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<OwnersAndReactors> ownersAndReactors;

    public void setOwnersAndReactors(List<OwnersAndReactors> ownersAndReactors) {
        this.ownersAndReactors = ownersAndReactors;
    }

    public List<OwnersAndReactors> getOwnersAndReactors() {
        return ownersAndReactors;
    }
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
//    private List<Reactors> reactors;

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

    public Owners() {
    }
}
