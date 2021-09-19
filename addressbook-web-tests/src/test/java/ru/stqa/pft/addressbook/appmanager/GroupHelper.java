package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

        //clickOnTheElement(By.name("selected[]"));
    }

    public void submitGroupModification() {
        clickOnTheElement(By.name("update"));
    }

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        //здесь создается новый объект GroupData - сразу заполняются значения (есть же конструктор)
        fillGroupForm(groupData);
        submitGroupCreation("submit");
        returnToGroupPage();

    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));

    }


    public int getGroupCount() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groups = new ArrayList<GroupData>(); //здесь сразу нельзя добавить значения - см.ниже GroupData - тип данных

        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        System.out.println("elements:  " + elements);
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id ,name, null, null);
            groups.add(group);
            //System.out.println("-----------> " + name);
            //System.out.println("-----------> " + group);
            //System.out.println("-----------> " + group.getGroupName());
            //System.out.println("-----------> " + group.getGroupFooter());
            //System.out.println("*************************************** ");
        }

        return groups;
    }
}
