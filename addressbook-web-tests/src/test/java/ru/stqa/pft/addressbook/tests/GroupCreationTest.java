package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().initGroupCreation();
        //здесь создается новый объект GroupData - сразу заполняются значения (есть же конструктор)
        app.getGroupHelper().fillGroupForm(new GroupData("Name", "test1", "test2"));
        app.getGroupHelper().submitGroupCreation("submit");
        app.getGroupHelper().returnToGroupPage();
        app.getGroupHelper().goLogOut("Logout");
    }

}
