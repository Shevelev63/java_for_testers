package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class AddDeleteTest extends TestBase {

    @Test
    public void canDeleteAdd() {
        if (!(app.hbm().getContactCount() == 0)) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().deleteAdd(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canDeleteAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", ""));
        }
        app.contacts().deleteAllContacts();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

    @Test
    void canDeleteContactInGroup() {
        var contact = new AddContact()
                .withFirstame(CommonFunction.randomString(3))
                .withLastame(CommonFunction.randomString(3))
                .withAddress(CommonFunction.randomString(3));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().deleteAdd2(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
    }

}
