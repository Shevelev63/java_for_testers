package tests;

import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class AddDeleteTest extends TestBase {

    @Test
    public void canDeleteAdd() {
        if (!(app.contacts().getCount() == 0)) {
            app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", ""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteAdd(oldContacts.get(index));
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canDeleteAllContactsAtOnce() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", ""));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());
    }

}
