package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DeleteContactFromGroup extends TestBase{
    @Test
    void canDeleteContactInGroup() {
        if (!(app.hbm().getContactCount() == 0)) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList();
        AddContact contact;
        GroupData groupData = group.get(0);
        var contactsInGroup = app.hbm().getContactsInGroup(groupData);

        if (contactsInGroup.isEmpty()) {
            var contactsInNotGroup = app.hbm().getContactsNotInGroup();
            if (contactsInNotGroup.isEmpty()) ;
            {
                app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "", "", "", "", "", ""));
            }
            contactsInNotGroup = app.hbm().getContactsNotInGroup();
            contact = contactsInNotGroup.get(0);
            app.contacts().addContactInGroup(contact, groupData);
        }
        contactsInGroup = app.hbm().getContactsNotInGroup();
        var deletesContact = contactsInGroup.get(0);
        app.contacts().deleteAdd2(deletesContact, groupData);
        var oldRelated = app.hbm().getContactsInGroup(groupData);
        var newRelated = new ArrayList<>(oldRelated);
        newRelated.remove(deletesContact);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
    }
}

