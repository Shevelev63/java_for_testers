package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AddContactInGroup extends TestBase{

    public static List<AddContact> createRandomContact() {
        return List.of(new AddContact()
                .withFirstame(CommonFunction.randomString(10))
                .withLastame(CommonFunction.randomString(10))
                .withAddress(CommonFunction.randomString(10))
                .withMobile(CommonFunction.randomString(10))
                .withEmail(CommonFunction.randomString(10)));
    }

    @ParameterizedTest
    @MethodSource("createRandomContact")
    void canAddContactInGroup(AddContact contact) {
        if (!(app.contacts().getCount() == 0)) {
            app.contacts().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList();
        AddContact contactAdd;
        GroupData groupData = group.getFirst();
        var contactsNotInGroup = app.hbm().getContactsNotInGroup();
        var oldRelated = app.hbm().getContactsInGroup(groupData);

        if (contactsNotInGroup.isEmpty()) {
            contactAdd = contactsNotInGroup.getFirst();
            app.contacts().inToGroup(contactAdd,groupData);
        }
        else {
            app.contacts().createAdd(contact);
            contact = contact.withId(app.hbm().getIdContactByName(contact.firstname()));
            app.contacts().inToGroup(contact,groupData);
            contactAdd = contact;
        }
        var newRelated = app.hbm().getContactsInGroup(groupData);
        var ContactListInGroup = new ArrayList<>(oldRelated);
        ContactListInGroup.add(contact);
        Assertions.assertEquals(Set.copyOf(newRelated), Set.copyOf(ContactListInGroup));
    }

}
