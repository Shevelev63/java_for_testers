package manager;

import model.GroupData;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

    private final ApplicationManager manager;
    private GroupData form;

    public GroupHelper(ApplicationManager manager) {
        this.manager = manager;
    }

    public void openGroupsPage() {
        if (! manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public boolean isGroupPresent(ApplicationManager manager) {
        openGroupsPage();
      return !manager.isElementPresent(By.name("selected[]"));
    }

    public void CreateGroup(GroupData groupData) {
        openGroupsPage();
        initGroupCreation(groupData);
        fillGroupForm(form);
        submitGroupCreation(groupData);
        returnGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnGroupsPage();
    }

    private static void submitGroupCreation(GroupData groupData) {
        groupData.manager().driver.findElement(By.name("submit")).click();
    }

    private static void initGroupCreation(GroupData groupData) {
        groupData.manager().driver.findElement(By.name("new")).click();
    }

    public void deleteGroup(ApplicationManager manager) {
        openGroupsPage();
        selectGroup();
        deleteSelectedGroup(manager);
        returnGroupsPage();
    }

    private static void deleteSelectedGroup(ApplicationManager manager) {
        manager.driver.findElement(By.name("delete")).click();
    }


    private void returnGroupsPage() {
        manager.driver.findElement(By.linkText("group page")).click();

    }

    private void submitGroupModification() {
        manager.driver.findElement(By.name("update")).click();

    }

    private void fillGroupForm(GroupData form) {
        this.form = form;
        type("group_name", "TEST6");
        type("group_header", "Group1");
        manager.driver.findElement(By.name("group_footer")).sendKeys("Group2");
    }

    private void type(String group_name, String TEST6) {
        manager.driver.findElement(By.name(group_name)).click();
        manager.driver.findElement(By.name(group_name)).sendKeys(TEST6);
    }

    private void initGroupModification() {
        manager.driver.findElement(By.name("edit")).click();

    }

    private void selectGroup() {
        manager.driver.findElement(By.name("selected[]")).click();

    }
}
