package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;


public class UserCreationTest extends TestBase{

    @Test
    public void testCreateUser() throws Exception {
        app.getNavigationHelper().gotoPageHome();
        Thread.sleep(5000);

        List<UserData> before = app.getContactHelper().getListUsers();

        UserData user = new UserData("first name", "middle name", "Zz-last name", "test1");
        app.getContactHelper().createNewUser(user, true);
        List<UserData> after = app.getContactHelper().getListUsers();

        //проверка на размер - пока отличается
        Assert.assertEquals(after.size(), before.size() + 1);

        //сравниваем уже по-фамильно
        System.out.println("before - not add a new user ---> " + before);

        before.add(user);

        Assert.assertEquals(after.size(), before.size());
        System.out.println("before + added a new one ---> " + before);
        System.out.println("after  ---> " + after);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        System.out.println("new HashSet<Object> before ---> " + new HashSet<Object>(before));
        System.out.println("new HashSet<Object> after  ---> " + new HashSet<Object>(after));

    }
}
