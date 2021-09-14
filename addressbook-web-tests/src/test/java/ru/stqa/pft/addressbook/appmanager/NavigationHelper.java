package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{


    public NavigationHelper(WebDriver driver) {
        super(driver);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1")) && driver.findElement(By.tagName("h1")).getText().equals("Groups") && isElementPresent(By.name("new"))) {
            return;
        }
        clickOnTheElement(By.linkText("groups"));
    }

    public void gotoPageHome() {
        if (isElementPresent(By.id("maintable"))){
            return;
        }
        clickOnTheElement(By.linkText("home"));
    }
}
