package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void initUserModify(int index) {
        driver.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    private void initUserModifyByID(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
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

    public void modify(UserData user) {
        initUserModifyByID(user.getId());
        fullUserCreationForm(user, false);
        submitUserModification();
    }

    public void deleteByIndex(int index) throws InterruptedException {
        selectUser(index);
        deleteSelectedUser();
        accertDialogWindow();
        Thread.sleep(3000);
    }

    public void delete(UserData user) throws InterruptedException {
        selectUserById(user.getId());
        deleteSelectedUser();
        accertDialogWindow();
        Thread.sleep(3000);
    }

    private void selectUserById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }

    public boolean isThereAUser() {
        return isElementPresent(By.xpath("//img[@alt='Edit']"));
    }

    public int getUserCount() {
        return driver.findElements(By.name("selected[]")).size();
    }


    public List<UserData> getListUsersWithInfo(){
        List<UserData> usersWithInfo = new ArrayList<UserData>(); //здесь сразу нельзя добавить значения - см.ниже GroupData - тип данных
        List<WebElement> elements = driver.findElements(By.name("entry"));

        for (WebElement element: elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String name = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData().withId(id).withLastName(lastName).withFirstName(name);
            usersWithInfo.add(user);
        }
        return usersWithInfo;
    }

    public Set<UserData> all(){
        Set<UserData> usersWithInfo = new HashSet<UserData>(); //здесь сразу нельзя добавить значения - см.ниже GroupData - тип данных
        List<WebElement> elements = driver.findElements(By.name("entry"));

        for (WebElement element: elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String name = cells.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData().withId(id).withLastName(lastName).withFirstName(name);
            usersWithInfo.add(user);
            System.out.println("----->" + user);
        }
        return usersWithInfo;
    }
}
