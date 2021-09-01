package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

    public void deteleSelectedGroups() {
        clickOnTheElement(By.xpath("//div[@id='content']/form/input[5]"));
    }

    public void selectGroup() {
        clickOnTheElement(By.name("selected[]"));
    }

}
