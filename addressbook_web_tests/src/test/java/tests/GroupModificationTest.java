package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTest extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().isGroupPresent(app)) {
            app.groups().CreateGroup(new GroupData(app));
        }
        app.groups().modifyGroup(new GroupData(app));
    }
}
