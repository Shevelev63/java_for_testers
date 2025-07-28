package tests;

import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class AddContactInGroup extends TestBase{

    @Test
    void canAddContactInGroup() {
        if (!(app.hbm().getContactCount() == 0)) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var newContacts = app.hbm().getContactList();
        var oldRelated = app.hbm().getContactsInGroup(group);
        var rnd = new Random();
        var index = rnd.nextInt(newContacts.size());
        app.contacts().addContactInGroup(new AddContact().withId(newContacts.get(index).id()));
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

}
