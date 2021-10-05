package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{


    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        typeValueInTheField(By.name("user"), username);
        typeValueInTheField(By.name("pass"), password);
        clickOnTheElement(By.xpath("//input[@value='Login']"));
    }
}
