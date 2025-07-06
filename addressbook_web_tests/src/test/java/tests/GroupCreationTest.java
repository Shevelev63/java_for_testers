package tests;

import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.ArrayList;
import java.util.List;

public class GroupCreationTest extends TestBase {


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroup(GroupData groupData) {
        int groupCount = app.groups().getCount();
        app.groups().CreateGroup(new GroupData());
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData()));
        for (var group_name : List.of("", "1")) {
            for (var group_header : List.of("", "2")) {
                for (var group_footer : List.of("", "3")) ;
                for (int i = 0; i < 5; i++) {
                    result.add(new GroupData());
                }
            }
        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData groupData) {
        int groupCount = app.groups().getCount();
        app.groups().CreateGroup(new GroupData());
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData(),
                new GroupData()));
        return result;
    }
}
