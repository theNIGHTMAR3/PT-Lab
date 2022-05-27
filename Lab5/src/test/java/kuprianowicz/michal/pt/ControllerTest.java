package kuprianowicz.michal.pt;

import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class ControllerTest {


    @Test
    public void Delete_Exist_Done()
    {
        //checks if returned value for deleting existing mage is "Done"
        MageRepository repo=mock(MageRepository.class);
        //doNothing().when(repo).delete("Gandalf");
        MageController controller = new MageController(repo);

        String result=controller.delete("Gandalf");

        assertThat(result).isEqualTo("Done");
    }

    @Test
    public void Delete_Not_Exist_NotFound()
    {
        //checks if returned value for deleting not existing mage is "Not found"
        MageRepository repo=mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(repo).delete("notExistingName");
        MageController controller = new MageController(repo);

        String result=controller.delete("notExistingName");

        assertThat(result).isEqualTo("Not found");
    }

    @Test
    public void Find_Not_Exists_NotFound()
    {
        //checks if returned value for finding not existing mage is "Not found"
        MageRepository repo=mock(MageRepository.class);
        MageController controller = new MageController(repo);

        String result=controller.find("notExistingName");

        assertThat(result).isEqualTo("Not found");
    }

    @Test
    public void Find_Exists_String()
    {
        //checks if returned value for finding existing mage is toString
        MageRepository repo=mock(MageRepository.class);
        Mage newMage=new Mage("Gandalf",7);
        doReturn(Optional.of(newMage)).when(repo).find("Gandalf");
        MageController controller = new MageController(repo);

        String result=controller.find("Gandalf");

        assertThat(result).isEqualTo(newMage.toString());
    }

    @Test
    public void Save_New_Done()
    {
        //checks if returned value for saving not existing mage is "Done"
        MageRepository repo=mock(MageRepository.class);
        MageController controller = new MageController(repo);

        String result=controller.save("Gandalf","8");

        assertThat(result).isEqualTo("Done");
    }

    @Test
    public void Save_New_BadRequest()
    {
        //checks if returned value for saving existing mage is "Bad request"
        MageRepository repo=mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(repo).save(any());
        MageController controller = new MageController(repo);
        controller.save("Gandalf","11");

        String result=controller.save("Gandalf","8");

        assertThat(result).isEqualTo("Bad request");
    }


}
