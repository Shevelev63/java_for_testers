package tests;

import model2.AddContact;
import org.junit.jupiter.api.Test;

public class ContactModoficationTest extends TestBase {
    @Test
    void canModifyContacts() {
        if (!app.contacts().isAddPresent()) {
            app.contacts().createAdd(new AddContact("Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com"));
        }
        app.contacts().modifyContacts(new AddContact());
    }
}
