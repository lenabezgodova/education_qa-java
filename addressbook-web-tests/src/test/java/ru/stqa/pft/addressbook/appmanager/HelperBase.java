package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void clickOnTheElement(By locator) {
        driver.findElement(locator).click();
    }

    protected void typeValueInTheField(By locator, String textValue) {
        clickOnTheElement(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(textValue);
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
