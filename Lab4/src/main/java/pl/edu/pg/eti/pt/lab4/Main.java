package pl.edu.pg.eti.pt.lab4;

import pl.edu.pg.eti.pt.lab4.entities.Mage;
import pl.edu.pg.eti.pt.lab4.entities.Test;
import pl.edu.pg.eti.pt.lab4.entities.Tower;
import pl.edu.pg.eti.pt.lab4.entities.jpa.MageJPA;
import pl.edu.pg.eti.pt.lab4.entities.jpa.TowerJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em;

        TowerJPA towerJPA = new TowerJPA(emf);
        MageJPA mageJPA = new MageJPA(emf);
        Test test=new Test(mageJPA,towerJPA);

        //test.AboveTests();

        test.RemoveTest();

        emf.close();
    }
}
