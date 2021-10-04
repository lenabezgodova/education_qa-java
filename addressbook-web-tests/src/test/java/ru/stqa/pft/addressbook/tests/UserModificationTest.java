package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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

        Users before = app.contact().all();
        UserData modifiedUser = before.iterator().next();
        System.out.println("modifiedUser ---> " + modifiedUser);

        UserData user = new UserData().withId(modifiedUser.getId()).withLastName("Ivanov").withMiddleName("Ivanovich").withFirstName("Ivan");

        app.contact().modify(user);
        app.goTo().pageHome();

        Users after = app.contact().all();

        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
        System.out.println("Before> " + after);
        System.out.println("After> " + after);
    }



}
