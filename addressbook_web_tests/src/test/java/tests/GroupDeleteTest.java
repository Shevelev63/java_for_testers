package tests;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupDeleteTest extends TestBase {

  @Test
  public void canDeleteGroup() {
    app.groups();
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().CreateGroup(new GroupData());
    }
    var oldGroup = app.hbm().getGroupList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroup.size());
    app.groups().deleteGroup(oldGroup.get(index));
    var newGroups = app.hbm().getGroupList();
    var expectedList = new ArrayList<>(oldGroup);
    expectedList.remove(index);
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  void canDeleteAllGroupsAtOnce() {
    if (app.hbm().getGroupCount() == 0) {
      app.hbm().CreateGroup(new GroupData());
    }
    app.groups().deleteAllGroups();
    Assertions.assertEquals(0, app.hbm().getGroupCount());
  }

}
