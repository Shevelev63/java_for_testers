package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpsessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private UserHelper createUserHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("firefox".equals(string)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(string)) {
                driver = new ChromeDriver();
            }
        else {
            throw new IllegalArgumentException(String.format("Unknown browser %s", string));
        }
        Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
        driver.get(properties.getProperty("web.baseUrl"));
        driver.manage().window().setSize(new Dimension(1936, 1056));
    }
        return driver;
      }

      public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
      }

    public HttpSessionHelper http() {
        if (httpsessionHelper == null) {
            httpsessionHelper = new HttpSessionHelper(this);
        }
        return httpsessionHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public UserHelper createUser() {
        if (createUserHelper == null) {
            createUserHelper = new UserHelper(this);
        }
        return createUserHelper;
    }
}