package testsContact;

import managerContact.ApplicationManagerContact;
import org.junit.jupiter.api.BeforeEach;

public class TestBaseAdd {

    protected static ApplicationManagerContact app;

    @BeforeEach
    public void setUp() {
        if (app == null){
            app = new ApplicationManagerContact();
            app.init2(System.getProperty("browser", "firefox"));
        }
    }
}
