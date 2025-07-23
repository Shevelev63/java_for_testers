package tests;

import common.CommonFunction;
import model2.AddContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(AddContact::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones2();
            Assertions.assertEquals(expected , phones);
        }


    @Test
    void testAddress() {
    var contacts = app.hbm().getContactList();
    var expected = contacts.stream().collect(Collectors.toMap(AddContact::id, contact ->
            Stream.of(contact.address())
                    .filter(s -> s != null && ! "".equals(s))
                    .collect(Collectors.joining("\n"))));
    var address = app.contacts().getAddress2();
            Assertions.assertEquals(expected , address);
}

    @Test
    void testEmail() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(AddContact::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))));
        var emails = app.contacts().getEmails2();
        Assertions.assertEquals(expected , emails);
    }
}
