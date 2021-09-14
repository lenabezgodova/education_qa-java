package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
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
        if (textValue != null){
            String existingText = driver.findElement(locator).getAttribute("value");
            //вопрос производительности! не надо вводить одно и тоже
            //если вводим значения не длинные - то тоже не очень поможет улучшить производительность
            if (! textValue.equals(existingText)){
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(textValue);
            }
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
           driver.findElement(locator);
           return true;
        } catch (NoSuchElementException ex){
            System.out.println("------------------------");
            System.out.println(ex.getSupportUrl());
            System.out.println("------------------------");
            return false;
        }
    }
}
