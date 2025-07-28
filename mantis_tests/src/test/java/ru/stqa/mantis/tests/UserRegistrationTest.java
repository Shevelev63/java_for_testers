package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTest extends TestBase{

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        var password = "Tktyf1130";
        app.jamesCli().addUser(email, password);
    }
}
