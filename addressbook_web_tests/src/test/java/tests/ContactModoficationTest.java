package tests;

import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModoficationTest extends TestBase {
    @Test
    void canModifyContacts() {
        if (!(app.contacts().getCount() == 0)) {
            app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com"));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testContact = new AddContact();
        app.contacts().modifyContacts(oldContacts.get(index), testContact);
        var newContacts = app.contacts().getList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testContact.withId(oldContacts.get(index).id()));
        Comparator<AddContact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
