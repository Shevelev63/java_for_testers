package manager;

import model2.AddContact;
import org.openqa.selenium.By;

public class ContactHelper extends HelperContactBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openAddPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("add new"));
        }
    }

    public boolean isAddPresent() {
        openAddPage();
        return manager.isElementPresent(By.name("selected[]"));
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

    public void deleteAdd() {
        openAddPage();
        selectContact();
        deleteSelectedContact();
    }

    private void deleteSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    public void modifyContacts(AddContact modifiedContacts) {
        openHomePage();
        selectContact();
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

    private void selectContact() {
        click(By.name("selected[]"));
    }
}
