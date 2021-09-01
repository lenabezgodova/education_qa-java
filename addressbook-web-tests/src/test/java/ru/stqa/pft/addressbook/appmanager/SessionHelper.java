package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.LoginData;

public class SessionHelper extends HelperBase{


    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(LoginData loginData) {
        typeValueInTheField(By.name("user"), loginData.getUsername());
        typeValueInTheField(By.name("pass"), loginData.getPassword());
        clickOnTheElement(By.xpath("//input[@value='Login']"));
    }
}
