package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase{

    @Test
    void canLogin() {
        app.session().login("administrator", "Tktyf1130");
        Assertions.assertTrue(app.session().isLoggedIn());
    }
}
