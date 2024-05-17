package entities;

import jakarta.persistence.*;
@Table(name = "ownersAndReactors")
@Entity
public class OwnersAndReactors {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reactor_id")
    private Reactors reactor;

    public OwnersAndReactors() {
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public Reactors getReactor() {
        return reactor;
    }

    public void setReactor(Reactors reactor) {
        this.reactor = reactor;
    }

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owners owner;

}
