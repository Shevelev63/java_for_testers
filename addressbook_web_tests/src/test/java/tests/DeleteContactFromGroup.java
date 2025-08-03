package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class DeleteContactFromGroup extends TestBase{
    @Test
    public void canRemoveContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData("", "test_group", "header", "footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var newContact = new AddContact()
                .withFirstame("canRemoveContactFromGroup test");

        app.contacts().createContactInGroup(newContact, group);
        var contactListInGroup = app.hbm().getContactsInGroup(group);

        Comparator<AddContact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        contactListInGroup.sort(compareById);
        var newContactWithId = contactListInGroup.get(contactListInGroup.size()-1);

        app.contacts().deleteAdd2(newContactWithId, group);

        var newContactListInGroup = app.hbm().getContactsInGroup(group);
        newContactListInGroup.sort(compareById);
        var expectedList = new ArrayList<>(contactListInGroup);
        expectedList.remove(expectedList.size()-1);
        Assertions.assertEquals(expectedList, newContactListInGroup);
    }
}

