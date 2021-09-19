package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.List;

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
        driver.findElement(By.name("submit")).click();
    }


    public void fullUserCreationForm(UserData userData, boolean creation) {
        typeValueInTheField(By.name("firstname"), userData.getFirstName());
        typeValueInTheField(By.name("middlename"),userData.getMiddleName());
        typeValueInTheField(By.name("lastname"), userData.getLastName());

        if (creation){
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void initUserCreation() {
        driver.findElement(By.linkText("add new")).click();
    }

    public void selectUser(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
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


    public void createNewUser(UserData userData, boolean b) {
        initUserCreation();
        fullUserCreationForm(userData, b);
        workWithDropDownBox();
        submitUserCreation();
        gotoHomePage();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public int getUserCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<UserData> getListUsers() {

        List<UserData> users = new ArrayList<UserData>(); //здесь сразу нельзя добавить значения - см.ниже UserData - тип данных
        int numberOfUsers = getUserCount();
        System.out.println("numberOfUsers: " + numberOfUsers);
        List<WebElement> elements = new ArrayList<WebElement>();

        for (int i = 2; i <= numberOfUsers+1; i++){
            String xPath = "//*[@id=\"maintable\"]/tbody/tr["+ i +"]/td[2]";
            System.out.println("------xPath-----> " + xPath);
            WebElement element = driver.findElement(By.xpath(xPath));
            elements.add(element);
        }

        for (WebElement element : elements){
            String name = element.getText();
            UserData user = new UserData(null, null, name, null);
            users.add(user);
        }


        // table has rows   - TODO
        List<WebElement> findOnRows = driver.findElements(By.xpath("//*[@id=\"maintable\"]/tbody/tr[5]"));
        //System.out.println("------findOnRows-----> " + findOnRows);
        for (WebElement element : findOnRows){
            String name = element.getText();
            //System.out.println("element.getText()---> " + name);
            //System.out.println("element.getText()---> " + element.getAttribute("id"));
            //UserData user = new UserData(name, null, null, null);
            //users.add(user);
        }



        return users;
    }
}
