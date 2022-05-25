package pl.edu.pg.eti.pt.lab4.entities.jpa;

import lombok.*;
import pl.edu.pg.eti.pt.lab4.entities.Mage;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MageJPA {
    private final EntityManagerFactory emf;

    public void AddMage(Mage mage)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();

        //save into DB
        em.persist(mage);
        em.getTransaction().commit();
        em.close();

        TowerJPA towerJPA= new TowerJPA(emf);
        towerJPA.AddMageToTower(mage,mage.getTower());

    }
    public void deleteMage(Mage mage)
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();

        //delete from DB
        em.remove(em.merge(mage));
        transaction.commit();
        em.close();
    }

    public void findAllMages() {
        EntityManager em = emf.createEntityManager();
        Query query=em.createQuery("SELECT m from Mage m", Mage.class);

        System.out.println("Wszyscy magowie: ");

        List<Mage> list = query.getResultList();
        if(list.size()==0)
            System.out.println("none");
        else
            for(Mage m : list)
            System.out.println(m);

        em.close();
    }

    public void FindByName(String name)
    {
        EntityManager em=emf.createEntityManager();
        //: - dynamiczny parametr
        Query query=em.createQuery("SELECT m from Mage m WHERE m.name=:name");
        //ustawienie parametru
        query.setParameter("name",name);

        Mage temp= (Mage) query.getSingleResult();

        System.out.println("Mag "+name+" znajduje się w wieży :"+temp.getTower());
    }

    public void FindMagesLevelAbove(int level)
    {
        EntityManager em=emf.createEntityManager();
        //: - dynamiczny parametr
        Query query=em.createQuery("SELECT m from Mage m WHERE m.level>:level");
        //ustawienie parametru
        query.setParameter("level",level);

        List<Mage> tempList=query.getResultList();

        System.out.println("Magowie z level wyższym niż "+level+" :");
        for(Mage temp : tempList)
        {
            System.out.println(temp);
        }
    }

    public void FindMagesLevelTower(int level,String towerName)
    {
        EntityManager em=emf.createEntityManager();
        //: - dynamiczny parametr
        Query query=em.createQuery("SELECT m from Mage m WHERE m.level>:level AND m.tower.name=:towerName");
        //ustawienie parametru
        query.setParameter("level",level);
        query.setParameter("towerName",towerName);

        List<Mage> tempList=query.getResultList();

        System.out.println("Magowie z levelem wyższym niż "+level+" i znajdujący się w wieży "+towerName+": ");
        for(Mage temp : tempList)
        {
            System.out.println(temp);
        }
    }

}
