package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToGroupPage() {
        clickOnTheElement(By.linkText("group page"));
    }

    public void submitGroupCreation(String buttonName) {
        clickOnTheElement(By.name(buttonName));
    }

    public void goLogOut(String buttonName) {
        clickOnTheElement(By.linkText(buttonName));
    }

    public void fillGroupForm(GroupData groupData) {
        typeValueInTheField(By.name("group_name"), groupData.getGroupName());
        typeValueInTheField(By.name("group_header"), groupData.getGroupHeader());
        typeValueInTheField(By.name("group_footer"), groupData.getGroupFooter());
    }


    public void initGroupCreation() {
        clickOnTheElement(By.name("new"));
    }

    public void initGroupModification(){
        clickOnTheElement(By.name("edit"));
    }

    public void deteleSelectedGroups() {
        clickOnTheElement(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroup(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();

    }

    public void submitGroupModification() {
        clickOnTheElement(By.name("update"));
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        //здесь создается новый объект GroupData - сразу заполняются значения (есть же конструктор)
        fillGroupForm(groupData);
        submitGroupCreation("submit");
        returnToGroupPage();

    }

    public void modifyById(int index, GroupData group) {
        selectGroup(index); //modify the last group
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId()); //modify the last group
        initGroupModification();
        fillGroupForm(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deteleSelectedGroups();
        returnToGroupPage();
    }

    private void selectGroupById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return driver.findElements(By.name("selected[]")).size();
    }


    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            //ищем внутри одного элемента другой элемент
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withGroupName(name));

        }
        return groups;

    }


}
