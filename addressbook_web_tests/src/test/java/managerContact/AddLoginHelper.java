package managerContact;

import org.openqa.selenium.By;

public class AddLoginHelper extends HelperContactBase {

    public AddLoginHelper(ApplicationManagerContact managerContact) {
        super(managerContact);
    }

    void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
