package manager;
import org.openqa.selenium.By;

public class HelperContactBase {
    protected final ApplicationManager manager;

    public HelperContactBase(ApplicationManager manager) {
        this.manager = manager;
    }

    protected void type(By locator, String text) {
        click(locator);
        manager.driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        manager.driver.findElement(locator).click();
    }
}
