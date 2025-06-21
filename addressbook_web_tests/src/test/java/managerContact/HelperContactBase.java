package managerContact;

import org.openqa.selenium.By;

public class HelperContactBase {
    protected final ApplicationManagerContact managerContact;

    public HelperContactBase(ApplicationManagerContact managerContact) {
        this.managerContact = managerContact;
    }

    protected void type(By locator, String text) {
        click(locator);
        managerContact.driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        managerContact.driver.findElement(locator).click();
    }
}
