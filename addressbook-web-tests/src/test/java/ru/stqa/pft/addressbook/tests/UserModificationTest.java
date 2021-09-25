package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;

public class UserModificationTest extends TestBase{

    @BeforeTest
    public void ensurePreconditions(){
        app.goTo().pageHome();

        if (! app.contact().isThereAUser()){
            app.contact().createNewUser(new UserData().withFirstName("first name").withMiddleName("middle name").withLastName("last name").withGroup("test1"), true);
        }
    }

    @Test
    public void testUpdateUser() throws Exception {

        List<UserData> before = app.contact().getListUsersWithInfo();
        int index = before.size() - 1;
        UserData user = new UserData().withId(before.get(index).getId()).withLastName("Ivanov").withMiddleName("Ivanovich").withFirstName("Ivan");
        System.out.println("before: size " + before.size() + " -->" + before);

        app.contact().modify(index, user);
        app.goTo().pageHome();

        List<UserData> after = app.contact().getListUsersWithInfo();
        System.out.println("after: " + after);

        before.remove(index);
        before.add(user);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        System.out.println("new HashSet<Object>(before) " + after);
        System.out.println("new HashSet<Object>(after) " + after);

    }



}
