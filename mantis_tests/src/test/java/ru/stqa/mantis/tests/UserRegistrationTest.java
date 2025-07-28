package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunction;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserRegistrationTest extends TestBase{

    public static List<String> randomUsername() {
        return List.of(CommonFunction.randomString(5));
    }

    @ParameterizedTest
    @MethodSource("randomUsername")
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        var password = "tktyf1130";
        app.jamesCli().addUser(email, password);
        app.createUser().createNewAccount(username, email);
        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (!matcher.find()) {
            throw new AssertionError("URL not found in email");
        }
        var url = text.substring(matcher.start(), matcher.end());
        if (url.isBlank()) {
            throw new AssertionError("URL is empty");
        }
        app.createUser().activateUserAccount(url);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
