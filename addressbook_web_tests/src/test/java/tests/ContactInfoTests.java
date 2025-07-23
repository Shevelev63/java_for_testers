package tests;

import org.junit.jupiter.api.Test;

public class ContactInfoTests extends TestBase{

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contacts().getPhones(contact);
    }


}
