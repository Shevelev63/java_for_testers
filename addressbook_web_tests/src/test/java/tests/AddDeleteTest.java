package tests;

import model2.AddContact;
import org.junit.jupiter.api.Test;

public class AddDeleteTest extends TestBaseAdd {

    @Test
    public void canDeleteAdd() {
        if (!app.contacts().isAddPresent()) {
            app.contacts().createAdd(new AddContact("Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com"));
        }
        app.contacts().deleteAdd();
    }

}
