package pl.edu.pg.eti.pt.lab4.entities;

import lombok.AllArgsConstructor;
import pl.edu.pg.eti.pt.lab4.entities.jpa.MageJPA;
import pl.edu.pg.eti.pt.lab4.entities.jpa.TowerJPA;

@AllArgsConstructor
public class Test {
    private final MageJPA mageJPA;
    private final TowerJPA towerJPA;

    public void AddTest()
    {
        Tower tower=new Tower("TOWER1",1337);
        towerJPA.AddTower(tower);

        mageJPA.AddMage(new Mage("Aaaa",10,tower));
        mageJPA.AddMage(new Mage("Bbbb",5,tower));
        mageJPA.AddMage(new Mage("Cccc",13,tower));
        System.out.println("mages add test");
        mageJPA.findAllMages();
        System.out.println("tower add test");
        towerJPA.FindAllTowers();
    }

    public void RemoveTest()
    {
        Tower tower=new Tower("TOWER1",1337);
        towerJPA.AddTower(tower);

        Mage mage1=new Mage("Aaaa",10,tower);
        Mage mage2=new Mage("Bbbb",5,tower);
        Mage mage3=new Mage("Cccc",11,tower);
        Mage mage4=new Mage("Dddd",16,tower);
        mageJPA.AddMage(mage1);
        mageJPA.AddMage(mage2);
        mageJPA.AddMage(mage3);
        mageJPA.AddMage(mage4);


        mageJPA.findAllMages();
        towerJPA.FindAllTowers();

        System.out.println("\n");

        towerJPA.deleteMage(tower);

        System.out.println("Po usunięciu:");
        mageJPA.findAllMages();
        towerJPA.FindAllTowers();

        //System.out.println("find with level test");
        //mageJPA.FindMagesLevelTower(11,"TOWER1");

    }

    public void AboveTests()
    {
        Tower tower1=new Tower("tower1",1337);
        towerJPA.AddTower(tower1);
        Tower tower2=new Tower("tower2",900);
        towerJPA.AddTower(tower2);
        Tower tower3=new Tower("tower3",1200);
        towerJPA.AddTower(tower3);
        Tower tower4=new Tower("tower4",1000);
        towerJPA.AddTower(tower4);

        Mage mage1=new Mage("Aaaa",15,tower1);
        Mage mage2=new Mage("Bbbb",5,tower1);
        Mage mage3=new Mage("Cccc",11,tower1);
        Mage mage4=new Mage("Dddd",16,tower2);
        Mage mage5=new Mage("Eeee",20,tower2);
        Mage mage6=new Mage("Ffff",1,tower3);
        Mage mage7=new Mage("Gggg",12,tower4);
        mageJPA.AddMage(mage1);
        mageJPA.AddMage(mage2);
        mageJPA.AddMage(mage3);
        mageJPA.AddMage(mage4);
        mageJPA.AddMage(mage5);
        mageJPA.AddMage(mage6);
        mageJPA.AddMage(mage7);


        mageJPA.findAllMages();

        towerJPA.FindAllTowers();
        System.out.println("\n");
        towerJPA.FindTowerHigh(1000);

        System.out.println("\n");
        mageJPA.FindMagesLevelTower(10,"tower1");


    }
}
