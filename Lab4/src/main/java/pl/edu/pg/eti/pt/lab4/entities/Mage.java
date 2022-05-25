package pl.edu.pg.eti.pt.lab4.entities;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Mage {
    @Id
    private String name;
    private int level;

    @ManyToOne
    private Tower tower;

    public Mage(String name, int level, Tower tower) {
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    public Mage(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void setTower(Tower tower) {

        this.tower = tower;
    }

    @Override
    public String toString() {
        return "Mage { " + "name= " + name  + ", level= " + level + ", tower= " + tower.getName() + " }";
    }
}
