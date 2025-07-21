package tests;

import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testGroups = new GroupData();
        app.groups().modifyGroup(oldGroups.get(index), testGroups);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testGroups.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
