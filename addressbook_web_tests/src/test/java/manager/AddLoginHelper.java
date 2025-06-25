package manager;
import org.openqa.selenium.By;

public class AddLoginHelper extends HelperBase {

    public AddLoginHelper(ApplicationManager manager) {
        super(manager);
    }

    public void login(String user, String password) {
        type(By.name("user"), user);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
