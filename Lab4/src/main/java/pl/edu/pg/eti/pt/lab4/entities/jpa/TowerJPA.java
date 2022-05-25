package pl.edu.pg.eti.pt.lab4.entities.jpa;

import lombok.*;
import pl.edu.pg.eti.pt.lab4.entities.Mage;
import pl.edu.pg.eti.pt.lab4.entities.Tower;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TowerJPA {
    private final EntityManagerFactory emf;

    private List<Mage> AddMage(Tower tower)
    {
        return tower.getMages();
    }


    public void AddTower(Tower tower)
    {
        EntityManager em= emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();

        //add to DB
        em.persist(tower);
        transaction.commit();
        em.close();
    }

    public void deleteMage(Tower tower)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();

        //delete from DB
        em.remove(em.merge(tower));
        transaction.commit();
        em.close();
    }

    public void FindAllTowers()
    {
        EntityManager em= emf.createEntityManager();
        Query query=em.createQuery("SELECT t FROM Tower t");
        List<Tower> towerList=query.getResultList();
        System.out.println("Wszystkie wieże: ");

        if(towerList.size()==0)
            System.out.println("none");
        else
            for(Tower tempTower:towerList)
                System.out.println(tempTower);

        em.close();
    }

    public void AddMageToTower(Mage mage, Tower tower)
    {
        List<Mage> newMages=AddMage(tower);
        if(newMages==null)
        {
            newMages= new LinkedList<Mage>();
        }
        newMages.add(mage);
        tower.setMages(newMages);
        UpdateTower(tower);

    }

    public void UpdateTower(Tower tower)
    {
        EntityManager em= emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();

        //update tower in DB
        em.merge(tower);
        transaction.commit();
        em.close();
    }

    public void FindTowerHigh(int height)
    {
        EntityManager em=emf.createEntityManager();
        //: - dynamiczny parametr
        Query query=em.createQuery("SELECT t from Tower t WHERE t.height>:height");
        //ustawienie parametru
        query.setParameter("height",height);

        List<Tower> tempList=query.getResultList();

        System.out.println("Wieże wyższe niż "+height+" :");
        for(Tower temp : tempList)
        {
            System.out.println(temp);
        }
    }



}
