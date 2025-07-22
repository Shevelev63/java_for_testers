package manager;
import model.GroupData;
import model2.AddContact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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
        selectorCreateAdd();
        openHomePage();
    }

    public void createAdd2(AddContact contact, GroupData group) {
        openAddPage();
        fillAddForm(contact);
        selectGroup(group);
        selectorCreateAdd();
        openHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }


    private void selectorCreateAdd() {
        click(By.cssSelector("input:nth-child(75)"));
    }

    public void deleteAdd(AddContact contact) {
        openHomePage();
        selectContact(contact);
        deleteSelectedContact();
    }

    public void deleteAdd2(AddContact contact, GroupData group) {
        openHomePage();
        selectGroupList(group);
        selectContact(contact);
        deleteSelectedContact();
        removeFromContact();
    }

    private void selectGroupList(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void deleteSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void modifyContacts(AddContact contact, AddContact modifiedContacts) {
        openHomePage();
        selectContact(contact);
        initContactModificatiion();
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

    private void initContactModificatiion() {
        click(By.xpath("//td[id]/a/img"));
    }

    private void selectContact(AddContact contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void removeFromContact() {
        click(By.linkText("remove"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteAllContacts() {
        openHomePage();
        selectAllContacts();
        deleteSelectedContact();
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
            var tds = manager.driver.findElements(By.cssSelector("td.center"));
            var lastname = tr.getAttribute("title");
            var firstname = tr.getAttribute("alt");
            var address = tr.getAttribute("accept");
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new AddContact().withId(id).withFirstame(lastname).withLastame(firstname).withAddress(address));
        }
        return contacts;
    }
}
