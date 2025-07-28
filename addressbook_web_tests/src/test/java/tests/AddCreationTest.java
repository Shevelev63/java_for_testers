package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunction;
import model.GroupData;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddCreationTest extends TestBase {

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(AddContact add) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createAdd(add);
        var newContacts = app.hbm().getContactList();
        Comparator<AddContact> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(add.withId(newContacts.get(newContacts.size() - 1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);

    }

    public static List<AddContact> contactProvider() throws IOException {
        var result = new ArrayList<AddContact>();
        for (var firstname : List.of("", "Pavel")) {
            for (var lastname : List.of("", "Fedorov")) {
                for (var address : List.of("", "Lenina")) {
                    for (var mobile : List.of("", "5455")) {
                        for (var email : List.of("", "1@gmail.com")) {
                            result.add(new AddContact()
                                    .withLastame(lastname)
                                    .withAddress(address)
                                    .withMobile(mobile)
                                    .withEmail(email));
                        }
                    }
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<AddContact>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(AddContact add) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createAdd(add);
        var newContacts = app.hbm().getContactList();
        Assertions.assertEquals(newContacts, oldContacts);
    }

    public static List<AddContact> negativeContactProvider() {
        var result = new ArrayList<AddContact>(List.of(
                new AddContact("", "Aleksiy'", "Ivanov", "", "", "","", "", "", "", "", "")));
        return result;
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new AddContact()
                .withFirstame(CommonFunction.randomString(3))
                .withLastame(CommonFunction.randomString(3))
                .withAddress(CommonFunction.randomString(3));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().CreateGroup(new GroupData());
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createAdd(contact);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

}
