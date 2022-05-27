package kuprianowicz.michal.pt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RepositoryTest {

    @Test
    public void Delete_Not_Exists()
    {
        //checks if found object for notExistingName is exception
        assertThatThrownBy(()->
        {
            MageRepository repo = new MageRepository();
            repo.delete("notExistingName");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Get_Not_Exists()
    {
        MageRepository repo = new MageRepository();

        Optional<Mage> toFind=repo.find("notExistingName");

        //checks if found object for notExistingName is Optional.empty
        assertThat(toFind).isEqualTo(Optional.empty());
    }

    @Test
    public void Find_Exists_String()
    {
        MageRepository repo=new MageRepository();
        Mage newMage=new Mage(("Gandalf"),10);
        repo.save(newMage);

        Optional <Mage> toFind=repo.find("Gandalf");

        //checks if found object for "Gandalf" is mage named "Gandalf"
        assertThat(toFind).isEqualTo(Optional.of(newMage));
    }

    @Test
    public void Save_With_Used_Name()
    {
        //checks if returned object for duplicating mages is exception
        Assertions.assertThatThrownBy(() ->
        {
            MageRepository repo = new MageRepository();
            repo.save(new Mage("Saruman", 20));
            repo.save(new Mage("Saruman", 15));
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
