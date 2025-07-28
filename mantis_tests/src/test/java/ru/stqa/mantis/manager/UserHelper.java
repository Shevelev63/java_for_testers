package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createNewAccount(String username, String email) {

    }

    public void activateUserAccount(String url) {
        manager.driver().get(url);
        editAccount();
        updateUser();
    }

    private void editAccount(){
        type(By.name("password"),"password");
        type(By.name("password_confirm"),"password");
    }

    private void updateUser() {
        click(By.cssSelector("button[type='submit'].btn-success"));
    }
}

