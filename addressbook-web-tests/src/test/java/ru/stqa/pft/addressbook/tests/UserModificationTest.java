package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserModificationTest extends TestBase{

    @BeforeTest
    public void ensurePreconditions(){
        app.goTo().pageHome();

        if (app.contact().all().size() == 0){
            app.contact().createNewUser(new UserData().withFirstName("first name").withMiddleName("middle name").withLastName("last name").withGroup("test1"), true);
        }
    }

    @Test
    public void testUpdateUser() throws Exception {

        Set<UserData> before = app.contact().all();
        UserData modifiedUser = before.iterator().next();
        System.out.println("modifiedUser ---> " + modifiedUser);

        UserData user = new UserData().withId(modifiedUser.getId()).withLastName("Ivanov").withMiddleName("Ivanovich").withFirstName("Ivan");

        app.contact().modify(user);
        app.goTo().pageHome();

        Set<UserData> after = app.contact().all();
        System.out.println("after: " + after);

        before.remove(modifiedUser);
        before.add(user);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(before, after);
        System.out.println("Before> " + after);
        System.out.println("After> " + after);

    }



}
