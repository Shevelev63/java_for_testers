package manager;
import model2.AddContact;
import org.openqa.selenium.By;

import java.awt.*;
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

    private void selectorCreateAdd() {
        click(By.cssSelector("input:nth-child(75)"));
    }

    public void deleteAdd(AddContact contact) {
        openHomePage();
        selectContact(contact);
        deleteSelectedContact();
    }

    private void deleteSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void modifyContacts(AddContact modifiedContacts) {
        openHomePage();
        selectContact(null);
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
    }

    private void initContactModificatiion() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    private void selectContact(AddContact contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
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
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<AddContact> getList() {
        var contacts = new ArrayList<AddContact>();
        var tds = manager.driver.findElements(By.cssSelector("td.center"));
        for (var td : tds) {
            var name = td.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new AddContact().withId(id).withFirstame(name));
        }
        return contacts;
    }
}
