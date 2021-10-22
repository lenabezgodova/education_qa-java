package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.sql.Time;
import java.util.*;

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
        typeValueInTheField(By.name("address"),userData.getAddress());
        typeValueInTheField(By.name("home"),userData.getHome());
        typeValueInTheField(By.name("mobile"),userData.getMobile());
        typeValueInTheField(By.name("work"),userData.getWorkPhone());
        typeValueInTheField(By.name("email"),userData.getEmailFirst());
        typeValueInTheField(By.name("email2"),userData.getEmailSecond());
        typeValueInTheField(By.name("email3"),userData.getEmailThird());
        attach(By.name("photo"), userData.getPhoto());

        if (creation){
            if (userData.getGroups().size() > 0){
                Assert.assertTrue(userData.getGroups().size() == 1);
                new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroups().iterator().next().getGroupName());
            }
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
        userCash = null;

    }

    public void modify(UserData user) {
        initUserModifyByID(user.getId());
        fullUserCreationForm(user, false);
        submitUserModification();
        userCash = null;
    }

    public void deleteByIndex(int index) throws InterruptedException {
        selectUser(index);
        deleteSelectedUser();
        accertDialogWindow();
        Thread.sleep(3000);
        userCash = null;
    }

    public void delete(UserData user) throws InterruptedException {
        selectUserById(user.getId());
        deleteSelectedUser();
        accertDialogWindow();
        userCash = null;
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

    private Users userCash = null;

    public Users all(){
        if (userCash != null){
            return new Users(userCash);
        }

        userCash = new Users();
        List<WebElement> elements = driver.findElements(By.name("entry"));
        for (WebElement element: elements){
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String name = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            String[] phones = allPhones.split("\n"); //перенос строки

            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            UserData user = new UserData().withId(id).withLastName(lastName).withFirstName(name)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
            userCash.add(user);
            //System.out.println("----->" + user);
        }
        return new Users(userCash);
    }

    public UserData infoFromEditForm(UserData user) {
        selectUserById(user.getId()); //выбираю чек-бокс
        initUserModifyByID(user.getId()); //сразу кликаю на карандаш. Открывается форма редактирования
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");

        String email1 = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");

        String address = driver.findElement(By.name("address")).getAttribute("value");
        driver.navigate().back();

        return new UserData().withId(user.getId()).withFirstName(firstname).withLastName(lastname).withHome(home)
                .withMobile(mobile).withWorkPhone(work).withEmailFirst(email1).withEmailSecond(email2).withEmailThird(email3).withAddress(address);
    }


    public void addUserToGroup(UserData user, GroupData groupToAdd) throws InterruptedException {
        selectUserById(user.getId()); //выбираю чек-бокс

        selectGroupByName(groupToAdd.getGroupName());
        Thread.sleep(3000);


        driver.findElement(By.name("add")).click();
        Thread.sleep(3000);
        gotoHomePage();
        userCash = null;
    }

    public void selectGroupByName(String name) {
        new Select(driver.findElement(By.name("to_group"))).selectByVisibleText(name);

    }
}
