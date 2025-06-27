package tests;

import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddDeleteTest extends TestBase {

    @Test
    public void canDeleteAdd() {
        if (!(app.contacts().getCount() == 0)) {
            app.contacts().createAdd(new AddContact("Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com"));
        }
        int addCount = app.contacts().getCount();
        app.contacts().deleteAdd();
        int newGroupCount = app.contacts().getCount();
        Assertions.assertEquals(addCount - 1, newGroupCount);
    }

    @Test
    void canDeleteAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createAdd(new AddContact("Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com"));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
