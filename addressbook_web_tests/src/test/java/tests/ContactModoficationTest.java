package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModoficationTest extends TestBase {
    @Test
    void canModifyContacts() {
        if (!(app.hbm().getContactCount() == 0)) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testContact = new AddContact();
        app.contacts().modifyContacts(oldContacts.get(index), testContact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testContact.withId(oldContacts.get(index).id()));
        Comparator<AddContact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canAddContactInGroup() {
        if (!(app.hbm().getContactCount() == 0)) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().addContactInGroup();
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }
}
