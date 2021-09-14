package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() throws Exception {
        app.getNavigationHelper().gotoPageHome();
        app.getContactHelper().initUserCreation();
        app.getContactHelper().fullUserCreationForm(new UserData("first name", "middle name", "last name", "test1"), true);
        app.getContactHelper().workWithDropDownBox();
        app.getContactHelper().submitUserCreation();
        app.getContactHelper().gotoHomePage();
    }
}
