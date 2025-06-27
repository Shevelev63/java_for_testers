package tests;

import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

public class AddCreationTest extends TestBase {

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(AddContact add) {
        int addCount = app.contacts().getCount();
        app.contacts().createAdd(add);
        int newAddCount = app.contacts().getCount();
        Assertions.assertEquals(addCount + 1, newAddCount);
    }

    public static List<AddContact> contactProvider() {
        var result = new ArrayList<AddContact>();
        for (var firstname : List.of("", "Pavel")) {
            for (var lastname : List.of("", "Fedorov")) {
                for (var address : List.of("", "Lenina")) {
                    for (var mobile : List.of("", "5455")) {
                        for (var email : List.of("", "1@gmail.com")) ;
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new AddContact(randomString(i * 5), randomString(i * 5), randomString(i * 5), randomString(i * 5), randomString(i * 5)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(AddContact add) {
        int addCount = app.contacts().getCount();
        app.contacts().createAdd(add);
        int newAddCount = app.contacts().getCount();
        Assertions.assertEquals(addCount, newAddCount);
    }

    public static List<AddContact> negativeContactProvider() {
        var result = new ArrayList<AddContact>(List.of(
                new AddContact("Aleksiy'", "Ivanov", "", "", "")));
        return result;
    }
}
