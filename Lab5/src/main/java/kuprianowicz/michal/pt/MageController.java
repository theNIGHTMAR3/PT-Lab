package kuprianowicz.michal.pt;

import java.util.Optional;

public class MageController {
    private final MageRepository repository;


    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    //returns mages name if found, "Not found" if not
    public String find(String name)
    {
        Optional<Mage> mageToFind=repository.find(name);

        if(mageToFind.isPresent())
            return mageToFind.get().toString();
        else
            return "Not found";
    }

    //return "done" if mage is found and deleted, "Not found" if not
    public String delete(String name)
    {
        try
        {
            repository.delete(name);
        }
        catch(IllegalArgumentException ex)
        {
            return "Not found";
        }
        return "Done";
    }


    //return "Done" if given mage is saved, "Bad request" if not
    public String save(String name, String level)
    {
        try
        {
            repository.save(new Mage(name,Integer.parseInt(level)));
        }
        catch(IllegalArgumentException ex)
        {
            return "Bad request";
        }
        return "Done";
    }
}
