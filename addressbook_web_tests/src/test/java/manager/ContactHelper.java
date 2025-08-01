package manager;
import model.GroupData;
import model2.AddContact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openAddPage() {
        if (!manager.isElementPresent(By.name("submit"))) {
            click(By.linkText("add new"));
        }
    }

    public void createAdd(AddContact contact) {
        openAddPage();
        fillAddForm(contact);
        submitContactCreation();
        openHomePage();
    }

    public void  createContactInGroup(AddContact contact, GroupData groupData) {
        openAddPage();
        initContactCreation();
        fillAddForm(contact);
        selectGroup2(groupData);
        submitContactCreation();
        openHomePage();
    }

    public void selectGroup2(GroupData groupData) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(groupData.id());
    }

    public void inToGroup(AddContact contactAdd, GroupData groupData) {
        openAddPage();
        selectContact(contactAdd);
        selectToGroup(groupData);
        addToContactInGroup();
    }

    private void selectToGroup(GroupData groupData) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(groupData.id());
    }


    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void addToContactInGroup() {
        click(By.linkText("add"));
    }

    private void groupSelection(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }


    private void selectorCreateAdd() {
        click(By.cssSelector("input:nth-child(75)"));
    }

    public void deleteAdd(AddContact contact) {
        openHomePage();
        selectContact(contact);
        removeFromGroup();
    }

    public void deleteAdd2(AddContact contactDelete, GroupData groupData) {
        openHomePage();
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(groupData.id());
        selectContact(contactDelete);
        manager.driver.findElement(By.name("remove")).click();
    }

    private void selectGroupList(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void modifyContacts(AddContact contact, AddContact modifiedContacts) {
        openHomePage();
        selectContact(contact);
        initContactModificatiion(contact);
        fillAddForm(modifiedContacts);
        updateContactModofication();
        returnToHomePage();
    }

    private void openHomePage() {
        returnToHomePage();
    }


    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    private void updateContactModofication() {
        click(By.name("update"));
    }

    private void fillAddForm(AddContact contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        attach(By.name("photo"), contact.photo());
    }

    private void initContactModificatiion(AddContact contact) {
        click(By.xpath(String.format("//input[@id='%s']/../..td[6]", contact.id())));
    }


    private void selectContact(AddContact contact) {
        click(By.cssSelector(String.format("input[id='%s']", contact.id())));
    }

    private void removeFromGroup() {
        click(By.linkText("remove"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }


    public void deleteAllContacts() {
        openHomePage();
        selectAllContacts();
        removeFromGroup();
    }

    private void selectAllContacts() {
        manager.driver.
                findElements(By.name("selected[]")).
                forEach(WebElement::click);
    }

    public List<AddContact> getList() {
        openHomePage();
        var contacts = new ArrayList<AddContact>();
        var trs = manager.driver.findElements(By.cssSelector("tr.entry"));
        for (var tr : trs) {
            var tds = tr.findElements(By.tagName("td"));
            String id = String.valueOf(Integer.parseInt(tds.get(0).findElement(By.cssSelector("input[type='checkbox']")).getAttribute("value")));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String address = tds.get(3).getText();
            contacts.add(new AddContact().withId(id).withFirstame(lastname).withLastame(firstname).withAddress(address));
        }
        return contacts;
    }

    private void selectContactGroup(GroupData group) {
        var wait = new WebDriverWait(manager.driver, Duration.ofSeconds(10));
        var selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("group")));
        var select = new Select(selectElement);
        select.selectByValue(group.id());
    }


        public String getPhones(AddContact contact) {
       return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../..td[6]", contact.id()))).getText();
    }

    public String getAddress(AddContact contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../..td[4]", contact.id()))).getText();
    }

    public String getEmails(AddContact contact) {
        return manager.driver.findElement(By.xpath(String.format("//input[@id='%s']/../..td[5]", contact.id()))).getText();
    }

    public Map<String, String> getPhones2() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row: rows) {
            var id = row.findElement(By.name("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddress2() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row: rows) {
            var id = row.findElement(By.name("input")).getAttribute("id");
            var address = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, address);
        }
        return result;
    }

    public Map<String, String> getEmails2() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row: rows) {
            var id = row.findElement(By.name("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, emails);
        }
        return result;
    }
}
