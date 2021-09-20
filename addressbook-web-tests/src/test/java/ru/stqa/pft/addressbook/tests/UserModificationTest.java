package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserModificationTest extends TestBase{

    @Test
    public void testUpdateUser() throws Exception {
        app.getNavigationHelper().gotoPageHome();

        if (! app.getContactHelper().isThereAUser()){
            app.getContactHelper().createNewUser(new UserData("first name", "middle name", "last name", "test1"), true);
        }
        Thread.sleep(5000);

        List<UserData> before = app.getContactHelper().getListUsersWithInfo();
        System.out.println("before: size " + before.size() + " -->" + before);

        app.getContactHelper().initUserModification(before.size() - 1);

        UserData user = new UserData(before.get(before.size() - 1).getId(), "Ivanov", "Ivan", "Ivanovich", null);
        app.getContactHelper().fullUserCreationForm(user, false);
        app.getContactHelper().submitUserModification();
        app.getNavigationHelper().gotoPageHome();

        List<UserData> after = app.getContactHelper().getListUsersWithInfo();
        System.out.println("after: " + after);

        before.remove(before.size() - 1);
        before.add(user);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        System.out.println("new HashSet<Object>(before) " + after);
        System.out.println("new HashSet<Object>(after) " + after);

    }

}
