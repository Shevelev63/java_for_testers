package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class AddContactPutInGroup extends TestBase{
    @Test
    public void canPutContactToGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData("","name", "header","footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        app.hbm().createAdd(new AddContact()
                .withFirstame(CommonFunction.randomString(10))
                .withLastame(CommonFunction.randomString(10)));
        var contactList = app.hbm().getContactList();
        Comparator<AddContact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        contactList.sort(compareById);
        var contact = contactList.get(contactList.size()-1);

        var previousContactListInGroup = app.hbm().getContactsInGroup(group);
        app.contacts().inToGroup(contact, group);
        var newContactListInGroup = app.hbm().getContactsInGroup(group);

        var expectedList = new ArrayList<>(previousContactListInGroup);
        newContactListInGroup.sort(compareById);
        expectedList.add(newContactListInGroup.get(newContactListInGroup.size()-1));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContactListInGroup);
    }
}
