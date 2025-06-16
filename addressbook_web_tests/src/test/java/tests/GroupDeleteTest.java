package tests;

import org.junit.jupiter.api.Test;

public class GroupDeleteTest extends TestBase {

  @Test
  public void canDeleteGroup() {
    app.groups();
    if (app.groups().isGroupPresent(app)) {
      app.groups().CreateGroup(app);
    }
    app.groups().deleteGroup(app);
  }

}
