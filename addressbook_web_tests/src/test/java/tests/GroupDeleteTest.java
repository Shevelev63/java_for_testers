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
    if (app.groups().getCount() == 0) {
      app.groups().CreateGroup(new GroupData());
    }
    var oldGroup = app.groups().getList();
    var rnd = new Random();
    var index = rnd.nextInt(oldGroup.size());
    app.groups().deleteGroup(oldGroup.get(index));
    var newGroups = app.groups().getList();
    var expectedList = new ArrayList<>(oldGroup);
    expectedList.remove(index);
    Assertions.assertEquals(newGroups, expectedList);
  }

  @Test
  void canDeleteAllGroupsAtOnce() {
    if (app.groups().getCount() == 0) {
      app.groups().CreateGroup(new GroupData());
    }
    app.groups().deleteAllGroups();
    Assertions.assertEquals(0, app.groups().getCount());
  }

}
