package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupCreationTest extends TestBase {


    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateMultipleGroup(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groups().CreateGroup(group);
        var newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var maxID = newGroups.get(newGroups.size() - 1).id();
        var extraGroups = newGroups.stream().filter(g -> ! oldGroups.contains(g)).toList();
        var newId = extraGroups.get(0).id();


        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxID));
        expectedList.sort(compareById);
        Assertions.assertEquals(Set.copyOf(newGroups), Set.copyOf(expectedList));
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

    public static Stream<GroupData> singleRandomGroup() {
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunction.randomString(10))
                .withHeader(CommonFunction.randomString(20))
                .withFooter(CommonFunction.randomString(30));
        return Stream.generate(randomGroup).limit(1);

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
