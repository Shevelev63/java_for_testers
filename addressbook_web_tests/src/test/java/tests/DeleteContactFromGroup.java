package tests;

import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeleteContactFromGroup extends TestBase{
    @Test
    void canDeleteContactInGroup() {
        if (!(app.hbm().getContactCount() == 0)) {
            app.hbm().createAdd(new AddContact("", "Ivanov", "Ivan", "Street1", "89325665", "2@yandex.com", "","" , "", "", "", ""));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var newContactAddGroup = app.hbm().getContactsInGroup(group);
        if (newContactAddGroup.isEmpty()) {
            var contactInGroup = app.hbm().getContactList().get(0);
            app.contacts().addContactInGroup(contactInGroup);
            newContactAddGroup = app.hbm().getContactsInGroup(group);
        }
        var contactDeletes = newContactAddGroup.get(0);
        app.contacts().deleteAdd2(group, contactDeletes);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(newContactAddGroup.size() - 1, newRelated.size());
    }
}
