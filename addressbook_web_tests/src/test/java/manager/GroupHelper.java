package manager;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public void CreateGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation(manager);
        fillGroupForm(group);
        submitGroupCreation(manager);
        returnGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnGroupsPage();
    }

    private static void submitGroupCreation(ApplicationManager manager) {
        manager.driver.findElement(By.name("submit")).click();
    }

    private static void initGroupCreation(ApplicationManager manager) {
        manager.driver.findElement(By.name("new")).click();
    }

    public void deleteGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        deleteSelectedGroups(manager);
        returnGroupsPage();
    }

    private static void deleteSelectedGroups(ApplicationManager manager) {
        manager.driver.findElement(By.name("delete")).click();
    }


    private void returnGroupsPage() {
        manager.driver.findElement(By.linkText("group page")).click();

    }

    private void submitGroupModification() {
        manager.driver.findElement(By.name("update")).click();

    }

    private void fillGroupForm(GroupData form) {
        createGroupFields();
    }

    private void createGroupFields() {
        type("group_name", "TEST8");
        type("group_header", "Group2");
        manager.driver.findElement(By.name("group_footer")).sendKeys("Group2");
    }

    private void type(String group_name, String TEST6) {
        manager.driver.findElement(By.name(group_name)).click();
        manager.driver.findElement(By.name(group_name)).sendKeys(TEST6);
    }

    private void initGroupModification() {
        manager.driver.findElement(By.name("edit")).click();

    }

    private void selectGroup(GroupData group) {
        manager.driver.findElement(By.cssSelector(String.format("input[value='%s']", group.id())));

    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllGroups() {
        openGroupsPage();
        selectAllGroups();
        deleteSelectedGroups(manager);
    }

    private void selectAllGroups() {
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }

    public List<GroupData> getList() {
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());
    }
}
