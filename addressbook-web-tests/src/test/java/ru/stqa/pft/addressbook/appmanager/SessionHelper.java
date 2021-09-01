package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.LoginData;

public class SessionHelper {

    private WebDriver driver;

    public SessionHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void login(LoginData loginData) {
        driver.findElement(By.name("user")).clear();
        driver.findElement(By.name("user")).sendKeys(loginData.getUsername());
        driver.findElement(By.name("pass")).clear();
        driver.findElement(By.name("pass")).sendKeys(loginData.getPassword());
        driver.findElement(By.xpath("//input[@value='Login']")).click();
    }
}
