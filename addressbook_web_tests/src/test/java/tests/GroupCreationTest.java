package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase {


    @Test
    public void canCreateGroup() {
        app.groups().CreateGroup(new GroupData(app));
    }


}
