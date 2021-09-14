package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() throws Exception {
        app.getNavigationHelper().gotoPageHome();
        Thread.sleep(5000);

        app.getContactHelper().createNewUser(new UserData("first name", "middle name", "last name", "test1"), true);
    }
}
