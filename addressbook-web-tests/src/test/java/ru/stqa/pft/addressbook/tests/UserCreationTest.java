package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() throws Exception {
        app.getNavigationHelper().gotoPageHome();
        Thread.sleep(5000);

        List<UserData> before = app.getContactHelper().getListUsers();
        System.out.println(before);
        app.getContactHelper().createNewUser(new UserData("first name", "middle name", "last name", "test1"), true);
        List<UserData> after = app.getContactHelper().getListUsers();
        System.out.println(after);

        Assert.assertEquals(after.size(), before.size() + 1);

    }
}
