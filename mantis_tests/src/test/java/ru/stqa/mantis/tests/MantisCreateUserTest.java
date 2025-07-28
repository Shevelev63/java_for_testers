package ru.stqa.mantis.tests;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class MantisCreateUserTest extends TestBase {


  @Test
  void createUser() {
    app.createUser().fullCreateUser();
    driver.findElement(By.linkText("Users")).click();
    driver.findElement(By.linkText("Create New Account")).click();
    driver.findElement(By.id("user-username")).click();
    driver.findElement(By.id("user-username")).sendKeys("12");
    driver.findElement(By.id("user-realname")).click();
    driver.findElement(By.id("user-realname")).sendKeys("12");
    driver.findElement(By.id("email-field")).click();
    driver.findElement(By.id("email-field")).sendKeys("user10@localhost");
    driver.findElement(By.xpath("//input[@value=\'Create User\']")).click();
    driver.findElement(By.linkText("Proceed")).click();
    driver.findElement(By.linkText("Users")).click();
  }
}
