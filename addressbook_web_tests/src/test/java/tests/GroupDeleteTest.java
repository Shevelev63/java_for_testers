package tests;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupDeleteTest extends TestBase {

  @Test
  public void canDeleteGroup() {
    app.groups();
    if (app.groups().getCount() == 0) {
      app.groups().CreateGroup(new GroupData(app));
    }
    int groupCount = app.groups().getCount();
    app.groups().deleteGroup(app);
    int newGroupCount = app.groups().getCount();
    Assertions.assertEquals(groupCount - 1, newGroupCount);
  }

  @Test
  void canDeleteAllGroupsAtOnce() {
    if (app.groups().getCount() == 0) {
      app.groups().CreateGroup(new GroupData(app));
    }
    app.groups().deleteAllGroups();
    Assertions.assertEquals(0, app.groups().getCount());
  }

}
