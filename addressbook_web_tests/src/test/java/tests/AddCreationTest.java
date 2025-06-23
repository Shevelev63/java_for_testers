package tests;

import model2.AddContact;
import org.junit.jupiter.api.Test;

public class AddCreationTest extends TestBase {

    @Test
    public void canCreateAdd() {
        app.contacts().createAdd(new AddContact());
    }

    @Test
    public void canCreateAddWithName() {
        app.contacts().createAdd(new AddContact().withFirstame("some firstname"));
    }

}
