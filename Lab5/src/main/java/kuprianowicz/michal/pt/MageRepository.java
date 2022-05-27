package kuprianowicz.michal.pt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private final Collection<Mage> collection;

    public MageRepository()
    {
        this.collection=new ArrayList<Mage>();
    }

    //return mage from collection
    private Mage findMage(String name)
    {
        for(Mage mage : collection)
        {
            if(mage.getName().equals(name))
                return mage;
        }
        return null;
    }

    //find mage with given name
    public Optional<Mage> find(String name)
    {
        Mage tempMage=findMage(name);
        if(tempMage!=null)
            return Optional.of(tempMage);
        return Optional.empty();
    }

    //save mage to collection
    public void save(Mage mage) throws IllegalArgumentException
    {
        Mage tempMage=findMage(mage.getName());
        if(tempMage==null)
            this.collection.add(mage);
        else{
            throw new IllegalArgumentException();
        }
    }

    //deletes mage with given name
    public void delete(String name)
    {
        Mage tempMage=findMage(name);

        if(tempMage!=null)
            collection.remove(tempMage);
        else
            throw new IllegalArgumentException();
    }
}
