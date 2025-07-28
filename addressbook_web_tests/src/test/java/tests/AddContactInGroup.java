package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddContactInGroup extends TestBase{

    public static List<AddContact> createRandomContact() {
        return List.of(new AddContact()
                .withFirstame(CommonFunction.randomString(10))
                .withLastame(CommonFunction.randomString(10))
                .withAddress(CommonFunction.randomString(10))
                .withMobile(CommonFunction.randomString(10))
                .withEmail(CommonFunction.randomString(10)));
    }

    @Test
    void canAddContactInGroup() {
        if (!(app.contacts().getCount() == 0)) {
            app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList();
        AddContact contact;
        GroupData groupData = group.get(0);
        var contactsNotInGroup = app.hbm().getContactsNotInGroup();

        if (contactsNotInGroup.isEmpty()) {
            app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", "", "", ""));
        }
        var oldRelated = app.hbm().getContactsInGroup(groupData);
        contactsNotInGroup = app.hbm().getContactsNotInGroup();
        contact = contactsNotInGroup.get(0);
        app.contacts().inToGroup(contact, groupData);
        var newRelated = app.hbm().getContactsInGroup(groupData);
        var ContactListInGroup = new ArrayList<>(oldRelated);
        ContactListInGroup.add(contact);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

}
