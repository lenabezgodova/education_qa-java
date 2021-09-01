package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.UserData;

public class ContactHelper extends HelperBase{


    public ContactHelper(WebDriver driver) {

        super(driver);
    }

    public void workWithDropDownBox() {
        driver.findElement(By.name("bday")).click();
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText("2");
        driver.findElement(By.name("bmonth")).click();
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("June");
    }

    public void gotoHomePage() {
        driver.findElement(By.linkText("home page")).click();
    }

    public void submitUserCreation() {
        driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }


    public void fullUserCreationForm(UserData userData) {
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys(userData.getFirstName());
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys(userData.getMiddleName());
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys(userData.getLastName());
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).clear();
        driver.findElement(By.name("nickname")).sendKeys(userData.getNickName());
//        driver.findElement(By.name("photo")).click();
//        driver.findElement(By.name("photo")).clear();
//        driver.findElement(By.name("photo")).sendKeys("C:\\fakepath\\ПЛАН-НА-МЕСЯЦ.jpg");
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys(userData.getTitle());
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys(userData.getCompanyName());
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys(userData.getAddressMain());
        driver.findElement(By.name("mobile")).click();
        driver.findElement(By.name("mobile")).clear();
        driver.findElement(By.name("mobile")).sendKeys(userData.getMobile());
        driver.findElement(By.name("fax")).click();
        driver.findElement(By.name("fax")).clear();
        driver.findElement(By.name("fax")).sendKeys(userData.getFax());
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys(userData.getEmailMain());
        driver.findElement(By.name("homepage")).click();
        driver.findElement(By.name("homepage")).clear();
        driver.findElement(By.name("homepage")).sendKeys(userData.getHomePage());
    }

    public void initUserCreation() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void selectUser() {
        clickOnTheElement(By.name("selected[]"));
    }

    public void deleteSelectedUser() {
        clickOnTheElement(By.xpath("//input[@value='Delete']"));
    }

    public void accertDialogWindow(){
        driver.switchTo().alert().accept();
    }

    public void declainDialogWindow(){
        driver.switchTo().alert().dismiss();
    }

    public void initUserModification() {
        clickOnTheElement(By.xpath("//img[@alt='Edit']"));
    }

    public void submitUserModification() {
        clickOnTheElement(By.name("update"));
    }
}
