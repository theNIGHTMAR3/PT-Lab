package pl.edu.pg.eti.pt.lab4.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class Tower {

    @Id
    private String name;
    private int height;


    @OneToMany(mappedBy="tower", cascade=CascadeType.ALL)
    private List<Mage> mages;

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public Tower(String name, int height, List<Mage> mages) {
        this.name = name;
        this.height = height;
        for(Mage mage: mages)
        {
            mage.setTower(this);
        }
        this.mages = mages;
    }


    @Override
    public String toString() {
        return "Tower { " + "name= " + name + ", height= " + height +", mages inside tower: "+mages.size() +" }";
    }
}
