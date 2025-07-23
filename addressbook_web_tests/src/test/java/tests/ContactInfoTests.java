package tests;

import common.CommonFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(contact -> contact.id(), contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && ! "".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones2();
            Assertions.assertEquals(expected , phones);
        }


    @Test
    void testAddress() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(4);
        var address = app.contacts().getAddress(contact);
        var expected = Stream.of(contact.address())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected , address);
    }

    @Test
    void testEmail() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(5);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected , emails);
    }
}
