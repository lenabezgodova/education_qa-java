package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTest extends TestBase{

    @BeforeTest
    public void ensurePreconditions(){
        if (app.db().users().size() == 0){
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withGroupName("Name").withGroupHeader("test1"));
            }
            app.goTo().pageHome();
            app.contact().createNewUser(new UserData().withFirstName("first name").withMiddleName("middle name")
                    .withLastName("last name").inGroup(app.db().groups().iterator().next()), true);
        }
    }

    @Test
    public void testUpdateUser(){

        Users before = app.db().users();
        UserData modifiedUser = before.iterator().next();
        System.out.println("modifiedUser ---> " + modifiedUser);

        UserData user = new UserData().withId(modifiedUser.getId()).withLastName("Ivanov").withMiddleName("Ivanovich")
                .withFirstName("Ivan").withMobile("900-800-700").withHome("456-00-90").withEmailFirst("email@ru.com")
                .withEmailSecond("secondemail@gmail.com");

        app.contact().modify(user);
        app.goTo().pageHome();

        Users after = app.db().users();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
        System.out.println("Before> " + after);

        verifyUserListInUI();

    }



}
