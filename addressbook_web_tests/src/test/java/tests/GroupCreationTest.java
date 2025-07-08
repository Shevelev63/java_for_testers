package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.File;
import java.io.IOException;
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

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData()));
 //       for (var name : List.of("", "1")) {
 //           for (var header : List.of("", "2")) {
 //               for (var footer : List.of("", "3")) {
 //                   result.add(new GroupData().withName(name).withHeader(header).withFooter(footer));
 //               }
 //           }
 //       }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>() {});
        result.addAll(value);
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
