package ru.stqa.mantis.tests;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.mantis.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static ApplicationManager app;
    protected static WebDriver driver;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"), properties);
        }
    }

    @BeforeEach
    public void setUp2() {
      driver = new FirefoxDriver();
        driver.get("http://localhost/mantisbt-2.26.4/login_page.php");
        driver.manage().window().setSize(new Dimension(1365, 938));
        driver.findElement(By.id("username")).sendKeys("administrator");
        driver.findElement(By.cssSelector(".width-40")).click();
        driver.findElement(By.id("password")).sendKeys("Tktyf1130");
        driver.findElement(By.cssSelector(".width-40")).click();
    }
}
